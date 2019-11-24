package com.hooplaapi.mongojobs

import com.mongodb.client.MongoDatabase
import org.bson.Document
import java.util.concurrent.CompletableFuture


abstract class MongoJob<T>(query: Document) : CompletableFuture<T>(){
    var query : Document

    init {
        this.query = query
    }

    abstract fun execute(database : MongoDatabase)
}