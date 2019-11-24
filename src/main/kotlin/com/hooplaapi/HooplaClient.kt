package com.hooplaapi

import java.io.PrintWriter
import java.net.Socket
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.collections.ArrayList

//size is appended in front of the message, it is 10 digits long, padded with zeroes if necessary
//1)Read the size of the message.

class HooplaClient(socket : Socket, Identifier : String, clientManager: ClientManager) : Thread() {
    private var hooplaMessageProcessor : HooplaMessageProcessor
    private var clientManager : ClientManager
    private var lockForMessageTray = ReentrantReadWriteLock(true)
    private var currentIndexForMessage = 0
    private var messageArray = ArrayList<Queue<String>>()
    private var printWriter : PrintWriter
    var identifier : String = ""

    init {
        this.hooplaMessageProcessor = HooplaMessageProcessor(socket)
        this.identifier = Identifier
        this.clientManager = clientManager
        this.printWriter = PrintWriter(socket.getOutputStream(), true)
        messageArray.add(LinkedList())
        messageArray.add(LinkedList())
    }

    override fun run() {
        //logInfo()
        while (true){
            hooplaMessageProcessor.process()
            var messages = hooplaMessageProcessor.getMessagePill()
            processMessages(messages)
            if (hooplaMessageProcessor.disconnected.get()){
                //logInfo("Disconnecting")
                clientManager.insertIntoQueue(this)
                return
            }
            sendMessages()
            sleep(50)
        }

    }

    private fun logInfo(s : String = "")
    {
        print(Thread.currentThread().id)
        println(" With Identifier id $identifier $s")
    }

    private fun sendMessages(){
        var indexToUse = currentIndexForMessage
        lockForMessageTray.writeLock().lock()
        if (currentIndexForMessage == 1){
            currentIndexForMessage = 0
        }
        else{
            currentIndexForMessage = 1
        }
        lockForMessageTray.writeLock().unlock()
        var messages = this.messageArray[indexToUse]

        while(!messages.isEmpty()){
            printWriter.print(messages.peek())
            printWriter.flush()
            messages.remove()
        }
    }

    fun sendMessage(message : String){
        lockForMessageTray.readLock().lock()
        this.messageArray[currentIndexForMessage].add(message)
        lockForMessageTray.readLock().unlock()
    }

    private fun botTest(message: String){
        sendMessage(message)
    }

    private fun processMessages(messages : Queue<String>){
        while (!messages.isEmpty()){
            //println(messages.peek().substring(0, 10))
            //println("Message size: " + messages.peek().length)
            botTest(messages.peek())
            messages.remove()
        }
    }

}