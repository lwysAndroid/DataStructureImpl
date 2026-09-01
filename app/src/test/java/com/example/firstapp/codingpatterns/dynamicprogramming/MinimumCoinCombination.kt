package com.example.firstapp.codingpatterns.dynamicprogramming

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class MinimumCoinCombination {

    @Test
    fun testMinimumCoinCombination() {
        printHyphensSeparation()
        testAndPrintMinimumCoinCombination(coinsValues = arrayOf(1, 2, 3), target = 5)
        testAndPrintMinimumCoinCombination(coinsValues = arrayOf(1, 2), target = 5)
        testAndPrintMinimumCoinCombination(coinsValues = arrayOf(2, 4), target = 5)
        testAndPrintMinimumCoinCombination(coinsValues = arrayOf(1, 4, 5), target = 24)
        testAndPrintMinimumCoinCombination(coinsValues = arrayOf(1, 4, 5, 6), target = 24)

    }


    private fun testAndPrintMinimumCoinCombination(coinsValues: Array<Int>, target: Int) {
        val minCombinations = minimumCoinCombination(coinsValues = coinsValues, target = target)
        val message = "Minimum Coin Combination for $target: $minCombinations"
        if (coinsValues.size < 100) {
            println(coinsValues.contentToString())
        }
        println(message)
        printHyphensSeparation()
    }

    private fun minimumCoinCombination(
        coinsValues: Array<Int>,
        target: Int,
        minimumCoinCombinationsStored: HashMap<Int, Int> = HashMap()
    ): Int {
        if (coinsValues.isEmpty()) {
            return -1
        }
        /* The first time the function is called we add the coin values to the hash map,
        * and we sort the coinsValues array */
        if (minimumCoinCombinationsStored.isEmpty()) {
            coinsValues.forEach { minimumCoinCombinationsStored[it] = 1 }
            coinsValues.sort()
        }

        val minimumCoinValue = coinsValues.first()

        // Base case
        if (/*target < 1 ||*/ target < minimumCoinValue) {
            return -1
        }


        /* Validate if a min combination value exists for the current target value in the hash map
         and return it */
        val minCombination = minimumCoinCombinationsStored[target]
        if (minCombination != null) {
            return minCombination
        }

        var minCombinationNewTarget = Int.MAX_VALUE
        coinsValues.forEach { currentCoin ->
            val newTarget = target - currentCoin
            minimumCoinCombination(
                coinsValues = coinsValues,
                target = newTarget,
                minimumCoinCombinationsStored = minimumCoinCombinationsStored
            ).also {
                if (it != -1 && it < minCombinationNewTarget) {
                    minCombinationNewTarget = it
                }
            }
        }
        return if (minCombinationNewTarget != Int.MAX_VALUE) {
            val minCombination = minCombinationNewTarget + 1
            minimumCoinCombinationsStored[target] = minCombination
            minCombination
        } else {
            -1
        }
    }
}
