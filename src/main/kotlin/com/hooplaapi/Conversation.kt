package com.hooplaapi

import com.mongodb.client.MongoDatabase


class Conversation(headId : Int = 0, numberOfMessages : Int = 0, conversationIdentifier: ConversationIdentifier) {
    private var currentHeadId : Int
    var numberOfMessages : Int
    val conversationIdentifier : ConversationIdentifier

    init {
        this.currentHeadId = headId
        this.numberOfMessages = numberOfMessages
        this.conversationIdentifier = conversationIdentifier
    }

    @Synchronized fun saveNewMessage(hooplaMessage: HooplaMessage, database: MongoDatabase) : Int{

    }

}



//that gets a headId for db
//Client one sends a message: first sent
//Client two sends a message: second sent

//Server will send back: a confirmation that the message has been received

//convId 1......n

//c2: 1 id
//c1: 2 id


