package com.hooplaapi

import org.bson.Document


class HooplaMessage(var messageIdNumber : Int, val toUniqueId : String, val fromUniqueId : String, val textMessage : String, val messageUniqueId : String){
    init {

    }

    public fun getDocument() : Document {
        val document = Document()
        document.append("_id", this.messageIdNumber);
        document.append("toUniqueId", this.toUniqueId)
        document.append("fromUniqueId", this.fromUniqueId)
        document.append("textMessage", this.textMessage)
        document.append("messageUniqueId", this.messageUniqueId)
        return document
    }
}
