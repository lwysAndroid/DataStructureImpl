package com.example.firstapp.codingpatterns.twopointers

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class PairSumSorted {

    @Test
    fun testPairSumSorted() {
        printHyphensSeparation()

        printTestPairSumSorted(sortedArray = arrayOf(-5, -2, 3, 4, 6), target = 7)
        printTestPairSumSorted(sortedArray = arrayOf(1, 1, 1), target = 2)
        printTestPairSumSorted(sortedArray = arrayOf(-5, -2, 3, 4, 6), target = -7)
        // Test Cases
        printTestPairSumSorted(sortedArray = emptyArray(), target = 0)
        printTestPairSumSorted(sortedArray = arrayOf(1), target = 1)
        printTestPairSumSorted(sortedArray = arrayOf(2, 3), target = 5)
        printTestPairSumSorted(sortedArray = arrayOf(2, 4), target = 5)
        printTestPairSumSorted(sortedArray = arrayOf(2, 2, 3), target = 5)
        printTestPairSumSorted(sortedArray = arrayOf(-1, 2, 3), target = 2)
        printTestPairSumSorted(sortedArray = arrayOf(-3, -2, 1), target = -5)
    }

    private fun printTestPairSumSorted(sortedArray: Array<Int>, target: Int) {
        val output = pairSumSorted(sortedArray = sortedArray, target = target)

        val outputsMessage = if (output != null) {
            val indexElementA = output.first
            val indexElementB = output.second
            val elementA = sortedArray[indexElementA]
            val elementB = sortedArray[indexElementB]

            "Indexes: $indexElementA, $indexElementB, values: $elementA + $elementB = $target"
        } else {
            "There aren't elements in the array that sum the target number"
        }
        val inputsMessage = "Sum $target from elements in ${sortedArray.contentToString()}"
        println(inputsMessage)
        println(outputsMessage)
        printHyphensSeparation()
    }

    private fun pairSumSorted(sortedArray: Array<Int>, target: Int): Pair<Int, Int>? {
        if (sortedArray.size <= 1) {
            return null
        }

        var leftIndex = 0
        var rightIndex = sortedArray.size - 1

        while (leftIndex < rightIndex) {
            val minValue = sortedArray[leftIndex]
            val maxValue = sortedArray[rightIndex]
            val currentSum = minValue + maxValue
            when {
                currentSum == target -> {
                    return Pair(first = leftIndex, second = rightIndex)
                }

                currentSum < target -> {
                    leftIndex++
                }

                // Case where: currentSum > target
                else -> {
                    rightIndex--
                }
            }
        }
        return null
    }
}
