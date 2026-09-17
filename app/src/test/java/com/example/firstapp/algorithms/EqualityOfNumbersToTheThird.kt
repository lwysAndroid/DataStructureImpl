package com.example.firstapp.algorithms

import com.example.firstapp.printHyphensSeparation
import org.junit.Test
import kotlin.collections.forEach

/*
* Print integers from the range (1-1000) to fit the equality
* a^3 + b^3 = c^3 + d^3
*/
class EqualityOfNumbersToTheThird {

    @Test
    fun testEqualityOfNumbersToTheThird() {
        printHyphensSeparation()
        testAndPrintEqualityOfNumbersToTheThird(startRange = 1, endRange = 1000)
        testAndPrintEqualityOfNumbersToTheThird(startRange = 2, endRange = 100)
    }

    private fun testAndPrintEqualityOfNumbersToTheThird(startRange: Int, endRange: Int) {
        val result = equalityOfNumbersToTheThird(startRange = startRange, endRange = endRange)
        println("Range $startRange to $endRange, size: ${result.size}")
        result.forEach { (value, pairs) ->
            println("$value: $pairs")
        }
        printHyphensSeparation()
    }

    private fun equalityOfNumbersToTheThird(
        startRange: Int,
        endRange: Int
    ): HashMap<Int, Set<Pair<Int, Int>>> {
        val range = startRange..endRange
        val resultsHashMap = HashMap<Int, Set<Pair<Int, Int>>>()

        range.forEach { outerValue ->
            range.forEach { innerValue ->
                val outerValueToTheThird = outerValue * outerValue * outerValue
                val innerValueToTheThird = innerValue * innerValue * innerValue

                val result = outerValueToTheThird + innerValueToTheThird
                val pair = if (outerValueToTheThird > innerValueToTheThird) {
                    Pair(innerValue, outerValue)
                } else {
                    Pair(outerValue, innerValue)
                }
                val setOfPairs = if (resultsHashMap.contains(key = result)) {
                    val currentSet = resultsHashMap[result]
                    val mutableSet = mutableSetOf(pair)
                    currentSet?.forEach { mutableSet.add(it) }
                    mutableSet
                } else {
                    setOf(pair)
                }
                resultsHashMap[result] = setOfPairs
            }
        }

        val validResultsHashMap = HashMap<Int, Set<Pair<Int, Int>>>()

        resultsHashMap.keys.forEach { currentKey ->
            val currentSet = resultsHashMap[currentKey]
            if (currentSet!!.size > 1) {
                validResultsHashMap[currentKey] = currentSet
            }
        }
        return validResultsHashMap
    }
}