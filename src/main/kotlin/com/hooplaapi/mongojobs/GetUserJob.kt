package com.hooplaapi.mongojobs

import com.mongodb.client.MongoDatabase
import org.bson.Document

class GetUserProfileJob(query : Document) : MongoJob<Document>(query){

    override fun execute(database : MongoDatabase) {
        var userProfileCollection = database.getCollection("UserProfile")
        var document = userProfileCollection.find(this.query).first()
        super.complete(document)
    }

}