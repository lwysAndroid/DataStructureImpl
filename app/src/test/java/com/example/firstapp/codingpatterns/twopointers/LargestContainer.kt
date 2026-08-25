package com.example.firstapp.codingpatterns.twopointers

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class LargestContainer {

    @Test
    fun testLargestContainer() {
        printHyphensSeparation()
        testAndPrintLargestContainerResults(arrayOf(2, 7, 8, 3, 7, 6))

        // Test cases
        testAndPrintLargestContainerResults(arrayOf())
        testAndPrintLargestContainerResults(arrayOf(1))
        testAndPrintLargestContainerResults(arrayOf(0, 1, 0))
        testAndPrintLargestContainerResults(arrayOf(3, 3, 3, 3))
        testAndPrintLargestContainerResults(arrayOf(1, 2, 3))
        testAndPrintLargestContainerResults(arrayOf(3, 2, 1))
    }

    private fun testAndPrintLargestContainerResults(array: Array<Int>) {
        val largestContainer = largestContainer(array = array)
        println(array.contentToString())
        println("largest container: $largestContainer")
        printHyphensSeparation()
    }


    private fun largestContainer(array: Array<Int>): Int {
        if (array.size < 2) {
            return 0
        }
        var pointerLeft = 0
        var pointerRight = array.size - 1
        var leftHeight = array[pointerLeft]
        var rightHeight = array[pointerRight]
        var maxArea = 0

        while (pointerLeft < pointerRight) {
            val currentArea = calculateArea(
                pointerLeft = pointerLeft,
                pointerRight = pointerRight,
                leftHeight = leftHeight,
                rightHeight = rightHeight
            )
            if (currentArea > maxArea) {
                maxArea = currentArea
            }

            when {
                leftHeight == rightHeight -> {
                    /*We have already obtained the maximum area for these two values.
                    When they are equal, it is because we are using the maximum possible
                    distance between them. If we move only one pointer, even if the next height
                    is infinite, the container's height remains limited by the original height.
                    Since the width decreases, we inevitably end up with a smaller area
                    (for example, min(6,6) * 8 = 6 * 8 = 48, whereas
                    min(6,1_000) * 7 = 6 * 7 = 42).
                    Therefore, after finding the area for two lines of equal height (thier maximum area),
                    the only way to potentially find a larger area is to move both pointers inward.

                   Therefore, since we can't increase height by moving just one pointer,
                   we can just move both pointers inward
                    */
                    pointerLeft++
                    leftHeight = array[pointerLeft]
                    pointerRight--
                    rightHeight = array[pointerRight]
                }

                leftHeight > rightHeight -> {
                    pointerRight--
                    rightHeight = array[pointerRight]
                }

                // leftHeight < rightHeight
                else -> {
                    pointerLeft++
                    leftHeight = array[pointerLeft]
                }
            }
        }

        return maxArea
    }

    private fun calculateArea(
        pointerLeft: Int,
        pointerRight: Int,
        leftHeight: Int,
        rightHeight: Int
    ): Int {
        val base = pointerRight - pointerLeft
        val height = if (leftHeight <= rightHeight) {
            leftHeight
        } else {
            rightHeight
        }
        return base * height
    }
}
