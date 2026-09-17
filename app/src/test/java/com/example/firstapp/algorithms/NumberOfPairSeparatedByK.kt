package com.example.firstapp.algorithms

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class NumberOfPairSeparatedByK {

    @Test
    fun testNumberOfPairSeparatedByK() {
        printHyphensSeparation()
        testAndPrintNumberOfPairSeparatedByK(arrayOf(1, 7, 5, 9, 2, 12, 3), separation = 2)
        testAndPrintNumberOfPairSeparatedByK(arrayOf(), separation = 1)
        testAndPrintNumberOfPairSeparatedByK(arrayOf(1, 7, 5, 9, 2, 12, 3), separation = 11)

    }

    private fun testAndPrintNumberOfPairSeparatedByK(array: Array<Int>, separation: Int) {
        val arrayOfPairs = numberOfPairSeparatedByK(array = array, separation = separation)
        println(array.contentToString())
        println(arrayOfPairs.contentToString())
        println("NumberOfPairSeparatedByK: ${arrayOfPairs.size}")
        printHyphensSeparation()
    }

    private fun numberOfPairSeparatedByK(
        array: Array<Int>,
        separation: Int
    ): Array<Pair<Int, Int>> {
        val numberSet = mutableSetOf<Int>()
        val pairsSet = mutableSetOf<Pair<Int, Int>>()

        array.forEach { currentNumber ->
            numberSet.add(element = currentNumber)
            val valueBellow = currentNumber - separation
            val valueAbove = currentNumber + separation

            if (numberSet.contains(element = valueBellow)) {
                pairsSet.add(element = Pair(first = valueBellow, second = currentNumber))
            }
            if (numberSet.contains(element = valueAbove)) {
                pairsSet.add(element = Pair(first = currentNumber, second = valueAbove))
            }
        }

        return pairsSet.toTypedArray()
    }

}
