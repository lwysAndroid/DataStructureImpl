package com.example.firstapp.algoexpert

fun caesarCipherEncryptor(string: String, key: Int): String {
    val hashMapLetterToNumber = HashMap<Char, Int>()
    val charArrayAlphabet = ('a'..'z').toList()
    charArrayAlphabet.forEachIndexed { index, char ->
        hashMapLetterToNumber[char] = index
    }

    val stringCharArray = string.toCharArray()
    stringCharArray.forEachIndexed { index, char ->
        var currentPosition = hashMapLetterToNumber.get(key = char)!!
        currentPosition += key
        currentPosition %= charArrayAlphabet.size
        stringCharArray[index] = charArrayAlphabet[currentPosition]
    }

    return stringCharArray.joinToString("")
}


fun testCaesarCipherEncryptor() {
    val word = "xyz"
    val shift = 2
    caesarCipherEncryptor(string = word, key = shift).also { print(it) }
}