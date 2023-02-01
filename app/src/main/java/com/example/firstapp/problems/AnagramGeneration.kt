package com.example.firstapp.problems

fun anagramGenerationRefactor(
    baseWord: String,
    progressAnagram: String = "",
    anagramArray: ArrayList<String> = ArrayList()
): ArrayList<String> {

    if (baseWord.length == 1) {
        val anagram = progressAnagram + baseWord
        anagramArray.add(anagram)
        return arrayListOf()
    }

    baseWord.forEachIndexed { index, currentChar ->
        val currentBaseWord = getStringWithoutCurrentCharacter(baseWord = baseWord, index)
        anagramGenerationRefactor(
            baseWord = currentBaseWord,
            progressAnagram = progressAnagram + currentChar,
            anagramArray = anagramArray
        )
    }
    return anagramArray
}

fun getStringWithoutCurrentCharacter(baseWord: String, index: Int): String {
    val stringWithoutCurrentCharacter =
        baseWord.toCharArray().toMutableList().let { charMutableList ->
            charMutableList.removeAt(index)
            val stringBuilder = StringBuilder()
            charMutableList.forEach { stringBuilder.append(it) }
            stringBuilder.toString()
        }
    return stringWithoutCurrentCharacter
}

fun testAnagramGeneration() {
    val word = "abc"
    anagramGenerationRefactor(word).also { strings ->
        println(strings.size)
        strings.forEach { println(":$it") }
    }
}