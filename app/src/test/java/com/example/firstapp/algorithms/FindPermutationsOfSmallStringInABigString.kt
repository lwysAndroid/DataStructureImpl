package com.example.firstapp.algorithms

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class FindPermutationsOfSmallStringInABigString {

    @Test
    fun testFindPermutationsOfSmallerStringInABiggerString() {
        printHyphensSeparation()
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
        bigString: String
    ) {
        val arrayOfPairs = findPermutationsOfSmallerStringInABiggerString(
            smallString = smallString,
            bigString = bigString,
        )
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
}
