package com.hooplaapi

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketTimeoutException
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class HooplaMessageProcessor(socket : Socket) {
    private var socket : Socket
    private var newMessage = true
    private var currentMessageSize = -1
    private var remainingMessageToRead = -1
    private var sizeCharBuffer : CharArray
    private var messageBuffer : CharArray
    private var readingSizeOfMessage = false
    private var readingMessage = false
    private val MAX_MESSAGE_SIZE = 10
    private var currentOffsetForReadingMessageSize = -1
    private var currentOffsetForReadingMessage = -1
    private var reader : BufferedReader
    private var messageQueue : Queue<String> //this is supposed to be the actual message pill
    var disconnected = AtomicBoolean(false)

    init {
        this.socket = socket
        this.socket.soTimeout = 1000
        this.reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        this.sizeCharBuffer = CharArray(0)
        this.messageBuffer = CharArray(0)
        this.messageQueue = LinkedList<String>()
    }

    fun process(){
        if (disconnected.get()){
            return
        }
        try {
            //println(messageBuffer.toString())
            if (newMessage) {
                sizeCharBuffer = CharArray(MAX_MESSAGE_SIZE);
                this.currentOffsetForReadingMessageSize = 0;
                var charRead = readSocket(sizeCharBuffer, currentOffsetForReadingMessageSize, MAX_MESSAGE_SIZE - currentOffsetForReadingMessageSize)
                if (charRead > 0){
                    newMessage = false
                    currentOffsetForReadingMessageSize += charRead
                    processMessageSize()
                }
                else if (charRead == -1){
                    //println("recieved -1 charRead3")
                    disconnected.set(true)
                }
            }
            else if (readingSizeOfMessage){
                var charRead = readSocket(sizeCharBuffer, currentOffsetForReadingMessageSize, MAX_MESSAGE_SIZE - currentOffsetForReadingMessageSize)
                if (charRead > 0){
                    currentOffsetForReadingMessageSize += charRead
                    processMessageSize()
                }
                else if (charRead == -1){
                    //println("recieved -1 charRead2")
                    disconnected.set(true)
                }
            }
            else if (readingMessage){
                var charRead = readSocket(messageBuffer, currentOffsetForReadingMessage, currentMessageSize - currentOffsetForReadingMessage)
                if (charRead > 0){
                    currentOffsetForReadingMessage += charRead
                    processMessage()
                }
                else if (charRead == -1){
                    //println("recieved -1 charRead1")
                    disconnected.set(true)
                }
            }
        }
        catch (ste : SocketTimeoutException){
            //nothing
        }
        catch (e : Exception){
            //e.printStackTrace();
            disconnected.set(true)
        }
    }

    fun getMessagePill() : Queue<String>{ //TODO: Change to MessagePill
        return messageQueue
    }

    private fun processMessageSize() {
        try {
            if (currentOffsetForReadingMessageSize == MAX_MESSAGE_SIZE) {
                currentMessageSize = Integer.parseInt(String(sizeCharBuffer))
                messageBuffer = CharArray(currentMessageSize)
                currentOffsetForReadingMessage = 0
                readingSizeOfMessage = false
                readingMessage = true
            } else {
                readingSizeOfMessage = true
            }
        }
        catch (e : Exception){
            disconnected.set(true)
        }
    }

    private fun processMessage(){
        if (currentOffsetForReadingMessage == currentMessageSize){
            messageQueue.add(String(messageBuffer))
            newMessage = true
            readingSizeOfMessage = false
            readingMessage = false
            sizeCharBuffer = CharArray(0) //clearing memory
            messageBuffer = CharArray(0) //clearing memory
        }
    }

    private fun readSocket(buffer : CharArray, offset : Int, len : Int) : Int{
        try {
            var amountRead = reader.read(buffer, offset, len)
            return amountRead
        }
        catch (exception : SocketTimeoutException){
            return 0
        }
        catch (exception : Exception){
            exception.printStackTrace()
            return -1
        }
    }

}