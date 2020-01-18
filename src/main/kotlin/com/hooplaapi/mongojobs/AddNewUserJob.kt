package com.hooplaapi.mongojobs

import com.mongodb.client.MongoDatabase
import org.bson.Document

class AddNewUserJob(query : Document) : MongoJob<Boolean>(query) {

    override fun execute(database: MongoDatabase) {
        try{
            val userProfileCollection = database.getCollection("UserProfile")
            userProfileCollection.insertOne(this.query)
            super.complete(true)
        }
        catch (e : Exception){
            super.complete(false)
        }
    }

}