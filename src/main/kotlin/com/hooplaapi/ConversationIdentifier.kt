package com.hooplaapi

class ConversationIdentifier(userID1 : String, userID2 : String) {
    val betterHalf : String
    val lesserHalf : String
    val conversationId : String

    init {
        if (userID1.compareTo(userID2) > 0)
        {
            betterHalf = userID1
            lesserHalf = userID2
            conversationId = userID1 + userID2
        }
        else{
            betterHalf = userID2
            lesserHalf = userID1
            conversationId = userID2 + userID1
        }
    }
}