package com.example.firstapp.codingpatterns.dynamicprogramming

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class JumpToTheEnd {

    @Test
    fun testJumpToTheEnd() {
        printHyphensSeparation()
        testAndPrintJumpToTheEnd(inputArray = arrayOf())
        testAndPrintJumpToTheEnd(inputArray = arrayOf(0))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(3))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(1, 2))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(0, 2))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(1, 0))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(2, 0))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(3, 2, 0, 1, 5))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(3, 2, 0, 1, 0))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(3, 2, 1, 0, 5))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(3, 2, 0, 2, 5))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(2, 2, 0, 2, 5))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(4, 2, 0, 2, 5))
        testAndPrintJumpToTheEnd(inputArray = arrayOf(2, 1, 0, 3))
    }

    private fun testAndPrintJumpToTheEnd(inputArray: Array<Int>) {
        val canGetToTheEnd = jumpToTheEnd(inputArray = inputArray)
        val message =
            "the array ${inputArray.contentToString()} canGetToTheEnd = $canGetToTheEnd "
        println(message)
        printHyphensSeparation()
    }

    private fun jumpToTheEnd(inputArray: Array<Int>, currentIndex: Int = 0): Boolean {
        // Edge case for empty array
        if (inputArray.isEmpty()) {
            return false
        }

        // Base case or End condition
        val lastIndex = inputArray.size - 1
        if (currentIndex == lastIndex) {
            return true
        } else {
            // Recursive case
            val currentValue = inputArray[currentIndex]
            if (currentValue == 0) {
                return false
            }
            (1..currentValue).forEach {
                val nexIndex = currentIndex + it
                // Validate all possible branches and return true if one of them gets to the End
                if (jumpToTheEnd(inputArray, nexIndex)) {
                    return true
                }
            }
            // Return false if no one of the branches get to the End when we validate all of them
            return false
        }
    }
}
