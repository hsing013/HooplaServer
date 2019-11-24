package com.hooplaapi

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

class ClientManager : Thread() {
    var QUIT = false
    private var cleanUpQueue : Queue<HooplaClient>
    private var currentUserOnlineTable : ConcurrentHashMap<String, HooplaClient>

    init {
        cleanUpQueue = ConcurrentLinkedQueue<HooplaClient>()
        currentUserOnlineTable = ConcurrentHashMap()
    }

    override fun run() {
        var tick = 0
        while(!QUIT){
            while (!cleanUpQueue.isEmpty()){
                val currentClient = cleanUpQueue.peek()
                cleanUpQueue.remove()
                currentUserOnlineTable.remove(currentClient.identifier)
                //println("Client manager is removing " + currentClient.identifier)
            }
            if ((tick % 15) == 0) {
                println("Current users active: " + currentUserOnlineTable.size)
                tick = 0
            }
            tick += 1
            sleep(100)
        }
    }

    fun insertIntoHashMap(identifier : String, client : HooplaClient){
        //println((Thread.currentThread().id).toString() + " is acccessing hashmap")
        currentUserOnlineTable.put(identifier, client)
    }

    fun insertIntoQueue(client : HooplaClient){
        //println((Thread.currentThread().id).toString() + " is acccessing cleanup queue")
        cleanUpQueue.add(client)
    }

    fun triggerQuitFlag(){
        QUIT = true
    }
}