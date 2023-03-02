package com.example.firstapp.algoexpert

fun main(args: Array<String>) {
    var str = listOf("flower", "flowers", "flow")
//    var str = listOf("rat", "car", "race")
    getPrefix(listOfWords = str).also {
        if (it.isEmpty()) {
            print("NO PREFIX")
        } else {
            print(it)
        }
    }
}

fun getPrefix(listOfWords: List<String>): String {

    val firstWord = listOfWords[0]
    var prefixCounter = 0
    var previousPrefixCounter = firstWord.length

    listOfWords.forEach { currentWord ->
        run charsValidation@{
            prefixCounter = 0
            currentWord.forEachIndexed { currentIndex, currentCharacter ->
                if (currentIndex >= firstWord.length) {
                    return@charsValidation
                }
                if (firstWord[currentIndex] == currentCharacter) {
                    prefixCounter++
                } else {
                    return@charsValidation
                }

            }
        }

        if (prefixCounter == 0) {
            return ""
        } else if (previousPrefixCounter >= prefixCounter) {
            previousPrefixCounter = prefixCounter
        }
    }

    return firstWord.subSequence(0, prefixCounter).toString()

}