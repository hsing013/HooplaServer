package com.hooplaapi

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

//COLLECTION: UserTable
//each document is userName, passWord, uniqueID
//loaded into RAM on boot up

//Collection: UniqueID
//stores data for an user
//FRIENDLIST Document: list of friends (uniqueID, userName, collection nameOfconversation) (restricted to 16 mb)
//NEWMESSAGES Document:

//COLLECTION:conversation between two people, name will be uniqueIDuniqueID
//will compare both ids, smallest in the front
//current Id : represents the Head of the conversation
//METADATA document: contains info about who is involved, number of messages
//each document is a text message, id, date, who sent it, type (TEXT, IMAGE)


//In Code
//  User
//      UserMetaData
//      ListofFriends -> share an unique Conversation object with each friend
//
//  Conversation
//      ID : atomic
//      numberOfMessages
//      uniqueId : nameOfCollection
//      lock : so that updates to the db are atomic
//
//
//

// Sending message
// Send message to friend
// Atomic update Id of conversation
// update database and if friend online send direct message else store in their NEWMESSAGE document
// Server will send back the updated ID of conversation

// If user online and they recieve direct message
// they will recieve conversationID and headID




fun main(){
    var list = ArrayList<Int>()
    list.add(98)
    list.add(45)
    var gson = Gson()
    var jsonParser = JsonParser()
    var json = JsonObject()
    var toJson = gson.toJson(list)
    var typeToken = object : TypeToken<ArrayList<Int>>() {}.type
    typeToken = object : TypeToken<IdentifierGenerator>() {}.type
    var jsonArray = JsonArray()
    var identifierGenerator = IdentifierGenerator()
    toJson = gson.toJson(identifierGenerator)
    //var fromJson = gson.fromJson<IdentifierGenerator>(toJson, typeToken)
    var conversationIdentifier = ConversationIdentifier("s", "soopla")

    print(conversationIdentifier.betterHalf)
}