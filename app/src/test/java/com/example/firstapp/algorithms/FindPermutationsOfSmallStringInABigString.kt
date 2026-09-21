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
            findPermutations(
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
        val stringsAreInvalid = smallString.isEmpty() || bigString.length < smallString.length

        if (stringsAreInvalid) {
            return emptyArray()
        }

        val smallStingHashMap = HashMap<Char, Int>()
        smallString.forEach { currentCharacter ->
            val currentAmount = smallStingHashMap[currentCharacter] ?: 0
            smallStingHashMap[currentCharacter] = currentAmount + 1
        }
        // Setup initial state
        var missingCharactersSet: MutableSet<Char> = smallStingHashMap.keys.toMutableSet()
        var missingCharactersHashMap = HashMap(smallStingHashMap)
        var extraCharactersHashMap = HashMap<Char, Int>()
        var counter = smallString.length

        val arrayListOfPairIndexes = arrayListOf<Pair<Int, Int>>()

        bigString.forEachIndexed { indexOfCurrentCharacter, currentCharacter ->
            val requiredAmountOfCurrentCharacter =
                smallStingHashMap.get(key = currentCharacter) ?: 0
            if (requiredAmountOfCurrentCharacter == 0) {
                /* If a character doesn't exist in the small string reset all values to stop taking
                * it into account the left part of the array
                */
                missingCharactersSet = smallStingHashMap.keys.toMutableSet()
                missingCharactersHashMap = HashMap(smallStingHashMap)
                extraCharactersHashMap = HashMap()
                counter = smallString.length
            } else {
                if (counter > 0) {
                    counter--
                }
                val amountOfMissingCharacter = missingCharactersHashMap[currentCharacter] ?: 0
                if (amountOfMissingCharacter > 0) {
                    val missingAmount = amountOfMissingCharacter - 1
                    missingCharactersHashMap[currentCharacter] = missingAmount
                    if (missingAmount == 0) {
                        missingCharactersSet.remove(element = currentCharacter)
                    }
                    if (missingCharactersSet.isEmpty()) {
                        arrayListOfPairIndexes.add(
                            Pair(
                                first = indexOfCurrentCharacter - (smallString.length - 1),
                                second = indexOfCurrentCharacter,
                            )
                        )
                    }
                } else {
                    (extraCharactersHashMap[currentCharacter] ?: 0).let {
                        extraCharactersHashMap[currentCharacter] = it + 1
                    }
                }

                /*Remove the left most character to continue taking into account the next one,
                * it shoul be removed either from the extraCharactersHashMap or added to the
                * missing characters
                */
                if (counter == 0) {
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
                            missingCharactersSet.add(leftMostCharacter)
                        }
                    }
                }
            }
        }

        return arrayListOfPairIndexes.toTypedArray()
    }

    /*
    * Standard Fixed Sliding Window Approach ({O}(N)$ Time, {O}(1)Space)
    * The standard way to solve the Find All Anagrams / Permutations in a String problem
    * is maintaining a fixed-size sliding window of length m using two frequency arrays
    * (or maps) and a matches counter tracking how many unique characters match the target
    * frequencies.
    * */
    fun findPermutations(smallString: String, bigString: String): Array<Pair<Int, Int>> {
        if (smallString.isEmpty() || bigString.length < smallString.length) {
            return emptyArray()
        }

        val sCount = IntArray(26)
        val windowCount = IntArray(26)

        // 1. Populate target frequency map
        for (char in smallString) {
            sCount[char - 'a']++
        }

        val results = mutableListOf<Pair<Int, Int>>()
        val m = smallString.length

        // 2. Slide window across bigString
        for (i in bigString.indices) {
            // Add right character to window
            windowCount[bigString[i] - 'a']++

            // Remove left character if window exceeds length M
            if (i >= m) {
                windowCount[bigString[i - m] - 'a']--
            }

            // Compare frequencies when window length reaches m
            if (i >= m - 1 && sCount.contentEquals(windowCount)) {
                val startIndex = i - m + 1
                results.add(Pair(startIndex, i))
            }
        }

        return results.toTypedArray()
    }
}
