package com.hooplaapi

import com.hooplaapi.mongojobs.MongoJob
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import java.util.concurrent.BlockingQueue

class MongoDBWorker(hostName : String, port : Int, jobsQueue : BlockingQueue<MongoJob<Any>>) : Thread() {
    private var currentJobs : BlockingQueue<MongoJob<Any>>
    private var mongoClient : MongoClient
    private var database : MongoDatabase
    private var QUIT_FLAG = false

    init {
        currentJobs = jobsQueue
        mongoClient = MongoClient(hostName, port)
        database = mongoClient.getDatabase("local")
    }

    override fun run() {
        while(QUIT_FLAG){
            try {
                var currentJob = currentJobs.take()
                currentJob.execute(database)
            }
            catch (ie : InterruptedException){
                continue
            }
            catch (e : Exception){
                println(e)
            }
        }
    }

    fun signalQuit(){
        QUIT_FLAG = true
    }


}