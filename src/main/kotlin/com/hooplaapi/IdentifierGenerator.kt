package com.hooplaapi

import kotlin.random.Random

class IdentifierGenerator(length: Int = 16) {
    private var lengthOfIdentifier : Int
    private var randomGenerator2 = Random(System.currentTimeMillis())
    private var randomGenerator1 = Random(System.currentTimeMillis())
    private var currentIdentifiersInUse : HashMap<String, String>

    init {
        if (length < 1){
            lengthOfIdentifier = 16
        }
        else{
            lengthOfIdentifier = length
        }
        currentIdentifiersInUse = HashMap()
    }

    fun generateIdentifier() : String{
        var identifier = ""

        for (x in 0 until lengthOfIdentifier){
            var firstChoice = randomGenerator1.nextInt(3)
            var char : Char
            if (firstChoice == 0){
                char = randomLowerCaseAlpha()
            }
            else if (firstChoice == 1){
                char = randomUpperCaseAlpha()
            }
            else{
                char = randomNumber()
            }
            identifier += char
        }

        if (currentIdentifiersInUse.containsKey(identifier)){
            return generateIdentifier()
        }
        else {
            currentIdentifiersInUse.put(identifier, identifier)
            return identifier
        }
    }

    fun removeIdentifier(identifier : String){
        if (currentIdentifiersInUse.containsKey(identifier)){
            currentIdentifiersInUse.remove(identifier)
        }
    }

    private fun randomUpperCaseAlpha() : Char{
        return (randomGenerator2.nextInt(26) + 65).toChar()
    }

    private fun randomLowerCaseAlpha() : Char{
        return (randomGenerator2.nextInt(26) + 97).toChar()
    }

    private fun randomNumber() : Char{
        return (randomGenerator2.nextInt(10) + 48).toChar()
    }


}