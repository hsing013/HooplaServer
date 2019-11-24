package com.hooplaapi

import com.hooplaapi.mongojobs.MongoJob
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class MongoJobMaster(host : String, port : Int, amountOfWorkers : Int = 8){
    private var QUIT = false
    private var mongoDBWorkers : ArrayList<MongoDBWorker>
    private var jobs : BlockingQueue<MongoJob<Any>>

    init {
        var workers = amountOfWorkers
        if (workers < 1){
            workers = 8
        }
        mongoDBWorkers = ArrayList()
        jobs = LinkedBlockingQueue()
        for (i in 1..workers){
            var newWorker = MongoDBWorker(host, port, jobs)
            newWorker.start()
            mongoDBWorkers.add(newWorker)
        }
    }

    fun submitJob(job : MongoJob<Any>){
        jobs.put(job)
    }
}