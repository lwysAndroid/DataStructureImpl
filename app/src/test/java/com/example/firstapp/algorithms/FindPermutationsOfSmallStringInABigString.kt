package com.example.firstapp.algorithms

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class FindPermutationsOfSmallStringInABigString {

    @Test
    fun testFindPermutationsOfSmallerStringInABiggerString() {
        printHyphensSeparation()
        testAndPrintFindPermutationsOfSmallerStringInABiggerString(
            smallString = "abbc",
            bigString = "abbca"
        )
        testAndPrintFindPermutationsOfSmallerStringInABiggerString(
            smallString = "abbc",
            bigString = "cbabadcbbabbcbabaabccbabc"
        )
        testAndPrintFindPermutationsOfSmallerStringInABiggerString(
            smallString = "",
            bigString = "a"
        )
        testAndPrintFindPermutationsOfSmallerStringInABiggerString(
            smallString = "abbc",
            bigString = "cba"
        )

    }

    private fun testAndPrintFindPermutationsOfSmallerStringInABiggerString(
        smallString: String,
        bigString: String,
        shouldUseImproved: Boolean = true
    ) {
        val arrayOfPairs = if (shouldUseImproved) {
            findPermutationsOfSmallerStringInABiggerStringImproved(
                smallString = smallString,
                bigString = bigString,
            )
        } else {
            findPermutationsOfSmallerStringInABiggerString(
                smallString = smallString,
                bigString = bigString,
            )
        }
        println("Small String: $smallString")
        println("Big String: $bigString")

        if (arrayOfPairs.isEmpty()) {
            println("No permutations found")
        } else {
            println("Permutations found: ${arrayOfPairs.size}")
            arrayOfPairs.forEach { pairOfIndexes ->
                val leftIndex = pairOfIndexes.first
                val rightIndex = pairOfIndexes.second
                val permutation = bigString.substring(leftIndex..rightIndex)
                println(permutation)
            }
        }
        printHyphensSeparation()

    }

    private fun findPermutationsOfSmallerStringInABiggerString(
        smallString: String,
        bigString: String
    ): Array<Pair<Int, Int>> {
        val stringsAreInvalid =
            smallString.isEmpty() || bigString.isEmpty() || smallString.length > bigString.length
        if (stringsAreInvalid) {
            return emptyArray()
        }

        val smallStringArray = smallString.toCharArray()
        val bigStringArray = bigString.toCharArray()
        val smallStringSize = smallStringArray.size

        val lettersAndAmountHashMap = HashMap<Char, Int>()
        smallStringArray.forEach { currentChar ->
            val currentAmount = lettersAndAmountHashMap[currentChar] ?: 0
            lettersAndAmountHashMap[currentChar] = currentAmount + 1
        }

        var leftIndex = 0
        var rightIndex = smallStringSize - 1

        val arrayWithPAirsOfIndexes = arrayListOf<Pair<Int, Int>>()

        while (rightIndex <= bigStringArray.size - 1) {
            val currentLettersAndAmountHashMap = HashMap<Char, Int>()
            var matches = 0
            var shouldSkipSmallStringSize = false

            run {
                (leftIndex..rightIndex).forEach { currentIndex ->
                    val currentLetter = bigStringArray[currentIndex]
                    val validAmount = lettersAndAmountHashMap[currentLetter]
                    if (validAmount == null) {
                        shouldSkipSmallStringSize = true
                        return@run
                    }
                    val savedAmount = currentLettersAndAmountHashMap[currentLetter] ?: 0
                    if (savedAmount >= validAmount) {
                        return@forEach
                    } else {
                        currentLettersAndAmountHashMap[currentLetter] = savedAmount + 1
                        matches++
                    }
                }
            }
            if (matches == smallStringSize) {
                arrayWithPAirsOfIndexes.add(Pair(first = leftIndex, second = rightIndex))
            }
            if (shouldSkipSmallStringSize) {
                leftIndex += smallStringSize
                rightIndex += smallStringSize
            } else {
                leftIndex++
                rightIndex++
            }
        }

        return arrayWithPAirsOfIndexes.toTypedArray()
    }

    private fun findPermutationsOfSmallerStringInABiggerStringImproved(
        smallString: String,
        bigString: String
    ): Array<Pair<Int, Int>> {
        val stringsAreInvalid =
            smallString.isEmpty() && bigString.isEmpty() && bigString.length < smallString.length

        if (stringsAreInvalid) {
            return emptyArray()
        }

        val arrayListOfPairIndexes = arrayListOf<Pair<Int, Int>>()
        val smallStingHashMap = HashMap<Char, Int>()
        smallString.forEach { currentCharacter ->
            val currentAmount = smallStingHashMap[currentCharacter] ?: 0
            smallStingHashMap[currentCharacter] = currentAmount + 1
        }
        // Setup initial state
        var missingCharactersHashMap = HashMap(smallStingHashMap)
        var extraCharactersHashMap = HashMap<Char, Int>()
        var counter = smallString.length

        bigString.forEachIndexed { indexOfCurrentCharacter, currentCharacter ->
            val requiredAmountOFCurrentCharacter =
                smallStingHashMap.get(key = currentCharacter) ?: 0
            if (requiredAmountOFCurrentCharacter == 0) {
                /* If a character doesn't exist in the small string reset all values to stop taking
                * it into account the left part of the array
                */
                counter = smallString.length
                missingCharactersHashMap = HashMap(smallStingHashMap)
                extraCharactersHashMap = HashMap()
            } else {
                if (counter > 0) {
                    counter--
                }
                val amountOfMissingCharacter = missingCharactersHashMap[currentCharacter] ?: 0
                if (amountOfMissingCharacter > 0) {
                    missingCharactersHashMap[currentCharacter] = amountOfMissingCharacter - 1
                    if (counter == 0) {
                        var areMissingCharacters = false
                        missingCharactersHashMap.forEach { (_, i) ->
                            if (i > 0) {
                                areMissingCharacters = true
                            }
                        }
                        if (!areMissingCharacters) {
                            arrayListOfPairIndexes.add(
                                Pair(
                                    first = indexOfCurrentCharacter - (smallString.length - 1),
                                    second = indexOfCurrentCharacter,
                                )
                            )
                        }
                    }
                } else {
                    (extraCharactersHashMap[currentCharacter] ?: 0).let {
                        extraCharactersHashMap[currentCharacter] = it + 1
                    }
                }

                if (counter == 0) {
                    /*Remove the left most character to continue taking into account the next one,
                    * it shoul be removed either from the extraCharactersHashMap or added to the
                    * missing characters
                    * */
                    val leftMostCharacter =
                        bigString[indexOfCurrentCharacter - (smallString.length - 1)]

                    val extraAmountOfLeftMostCharacter =
                        extraCharactersHashMap[leftMostCharacter] ?: 0
                    if (extraAmountOfLeftMostCharacter > 0) {
                        extraCharactersHashMap[leftMostCharacter] =
                            extraAmountOfLeftMostCharacter - 1
                    } else {
                        (missingCharactersHashMap[leftMostCharacter] ?: 0).let {
                            missingCharactersHashMap[leftMostCharacter] = it + 1
                        }
                    }
                }
            }
        }

        return arrayListOfPairIndexes.toTypedArray()
    }
}
