package com.hooplaapi.mongojobs

import com.mongodb.client.MongoDatabase
import org.bson.Document

class AddToConversationJob(query : Document, conversationCollectionId: String) : MongoJob<Boolean>(query) {
    private var conversationCollectionId = conversationCollectionId

    override fun execute(database: MongoDatabase) {
        try {
            val collection = database.getCollection(conversationCollectionId)
            collection.insertOne(query)
            super.complete(true)
        }
        catch (e : Exception){
            super.complete(false)
        }
    }
}