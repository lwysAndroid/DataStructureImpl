package com.example.firstapp.codingpatterns.twopointers

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class TripletSumSorted {

    @Test
    fun testTripletSumSorted() {
        printHyphensSeparation()

//        printTestTripletSumSorted(array = arrayOf(0, -1, 2, -3, 1))
//        printTestTripletSumSorted(array = arrayOf(-1, 2, -2, 1, -1, 2))
        printTestTripletSumSorted(array = arrayOf(3, 3, 3, -2, -2, -2, -1, -1, -1))
//        printTestTripletSumSorted(array = arrayOf(-4, -4, -2, 0, 0, 1, 2, 3))
//        printTestTripletSumSorted(array = arrayOf(4, 4, 2, 0, 0, 0, 1, 2, 3))
//        printTestTripletSumSorted(array = arrayOf(4, 4, 2, 0, 1, 2, 3))

        // Test cases
//        printTestTripletSumSorted(array = arrayOf())
//        printTestTripletSumSorted(array = arrayOf(0))
//        printTestTripletSumSorted(array = arrayOf(1, -1))
//        printTestTripletSumSorted(array = arrayOf(0, 0, 0))
//        printTestTripletSumSorted(array = arrayOf(1, 0, 1))
//        printTestTripletSumSorted(array = arrayOf(0, 0, 1, -1, 1, -1))
    }

    private fun printTestTripletSumSorted(array: Array<Int>) {
        val arrayOfSumItems = tripletSumSorted(array = array)
        println("Input array: ${array.contentToString()}")
        println("Sub arrays that sum 0: ${arrayOfSumItems.size}")
        arrayOfSumItems.forEach {
            println("$it , sum: ${it.sum()}")
        }
        printHyphensSeparation()
    }

    private fun tripletSumSorted(array: Array<Int>): List<List<Int>> {
        // Setup arrays
        val listOfSumItems: MutableList<List<Int>> = mutableListOf()
        val sortedArray = array.sortedArray()
        // println("sortedArray ${sortedArray.contentToString()}")

        // Foreach element in the sorted array
        sortedArray.forEachIndexed { index, value ->
            // It is impossible for the sum of three positive numbers to be equal to 0.
            // and as we have a sorted array all the remaining numbers should be positive
            // after one positive value
            if (value > 0) {
                return listOfSumItems
            }
            // Avoid duplicate search of repeated values
            val isRepeatedValue = if (index >= 1) {
                val previousValue = sortedArray[index - 1]
                previousValue == value
            } else {
                false
            }

            if (isRepeatedValue) {
                // This `return` acts like a `continue` to avoid processing the current repeated value.
                // so we can continue with the next item
                return@forEachIndexed
            }

            // Start with more external indices
            var leftIndex = index + 1
            var rightIndex = array.size - 1
            // Get the value with opposite sign of the current value to use it as a target
            val valueWithOppositeSign = (-1) * value

            // Find all possible sum combinations in the remaining array
            while (leftIndex < rightIndex) {
                val pairFound = pairSumSorted(
                    sortedArray = sortedArray,
                    target = valueWithOppositeSign,
                    leftIndex = leftIndex,
                    rightIndex = rightIndex
                )
                if (pairFound != null) {
                    val leftIndexOfPairFound = pairFound.first
                    val rightIndexOfPairFound = pairFound.second
                    val leftValueOfPairFound = sortedArray[leftIndexOfPairFound]
                    val rightValueOfPairFound = sortedArray[rightIndexOfPairFound]
                    listOfSumItems.add(listOf(value, leftValueOfPairFound, rightValueOfPairFound))
                    leftIndex = leftIndexOfPairFound + 1
                    rightIndex = rightIndexOfPairFound - 1

                    // Avoid duplicate search on repeated pair sum values
                    var leftValue = sortedArray[leftIndex]
                    while (leftValue == leftValueOfPairFound && leftIndex <= rightIndex) {
                        leftIndex++
                        leftValue = sortedArray[leftIndex]
                    }
                } else {
                    leftIndex = rightIndex
                }
            }
        }

        return listOfSumItems
    }

    private fun pairSumSorted(
        sortedArray: Array<Int>,
        target: Int,
        leftIndex: Int = 0,
        rightIndex: Int = sortedArray.size - 1,
    ): Pair<Int, Int>? {
        if (sortedArray.size <= 1) {
            return null
        }

        var currentLeftIndex = leftIndex
        var currentRightIndex = rightIndex

        while (currentLeftIndex < currentRightIndex) {
            val minValue = sortedArray[currentLeftIndex]
            val maxValue = sortedArray[currentRightIndex]
            val currentSum = minValue + maxValue
            when {
                currentSum == target -> {
                    return Pair(first = currentLeftIndex, second = currentRightIndex)
                }

                currentSum < target -> {
                    currentLeftIndex++
                }

                // Case where: currentSum > target
                else -> {
                    currentRightIndex--
                }
            }
        }
        return null
    }
}
