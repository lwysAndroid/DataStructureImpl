package com.example.firstapp.algorithms

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class FindElementsInCommon {

    @Test
    fun testFindElementsInCommon() {
        printHyphensSeparation()
        testAndPrintFindElementsInCommon(
            firstArray = arrayOf(13, 27, 35, 40, 49, 55, 59),
            secondArray = arrayOf(17, 35, 39, 40, 55, 58, 60)
        )
    }

    private fun testAndPrintFindElementsInCommon(firstArray: Array<Int>, secondArray: Array<Int>) {
        val elementsInCommonArray =
            findElementsInCommon(firstArray = firstArray, secondArray = secondArray)
        println("First Array: ${firstArray.contentToString()}")
        println("Second Array: ${secondArray.contentToString()}")
        println("Elements in Common Array: ${elementsInCommonArray.contentToString()}")

        printHyphensSeparation()
    }

    private fun findElementsInCommon(firstArray: Array<Int>, secondArray: Array<Int>): Array<Int> {
        val elementsInCommon = mutableListOf<Int>()
        var indexFirstArray = 0
        var indexSecondArray = 0
        val sizeArray = firstArray.size

        while (indexFirstArray < sizeArray && indexSecondArray < sizeArray) {
            val firstArrayElement = firstArray[indexFirstArray]
            val secondArrayElement = secondArray[indexSecondArray]
            when {
                firstArrayElement == secondArrayElement -> {
                    elementsInCommon.add(firstArrayElement)
                    indexFirstArray++
                    indexSecondArray++
                }

                firstArrayElement > secondArrayElement -> {
                    indexSecondArray++
                }

                // firstArrayElement < secondArrayElement
                else -> {
                    indexFirstArray++
                }
            }
        }

        return elementsInCommon.toTypedArray()
    }
}