package com.hooplaapi


data class HooplaMessage(var messageId : Int, val toUniqueId : String, val fromUniqueId : String, val textMessage : String, val clientUniqueId : String)
