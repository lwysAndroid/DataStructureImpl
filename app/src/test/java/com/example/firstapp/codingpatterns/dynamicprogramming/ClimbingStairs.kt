package com.example.firstapp.codingpatterns.dynamicprogramming

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

/*
* Determine the number of distinct ways to climb a staircase of n steps by taking either 1 or 2
* steps at a time
*
* */
class ClimbingStairs {

    @Test
    fun testClimbingStairs() {
        printHyphensSeparation()
        testAndPrintClimbingStairs(steps = -10)
        testAndPrintClimbingStairs(steps = 0)
        testAndPrintClimbingStairs(steps = 1)
        testAndPrintClimbingStairs(steps = 2)
        testAndPrintClimbingStairs(steps = 3)
        testAndPrintClimbingStairs(steps = 4)
        testAndPrintClimbingStairs(steps = 5)
        testAndPrintClimbingStairs(steps = 6)
        testAndPrintClimbingStairs(steps = 600)
    }

    private fun testAndPrintClimbingStairs(steps: Int) {
        val ways = waysToClimbAStaircase(steps = steps)
        val message = "Ways to climb a staircase of $steps steps: $ways"
        println(message)
        printHyphensSeparation()
    }

    private fun waysToClimbAStaircase(
        steps: Int,
        stepsCalculated: HashMap<Int, Int> = HashMap()
    ): Int {
        when {
            steps <= 0 -> {
                return 0
            }

            steps == 1 -> { // Base case
                return 1
            }

            steps == 2 -> { // Base case
                return 2
            }
        }

        var ways = stepsCalculated[steps]
        if (ways == null) {
            ways = waysToClimbAStaircase(
                steps - 1,
                stepsCalculated = stepsCalculated
            ) + waysToClimbAStaircase(steps - 2, stepsCalculated = stepsCalculated)
            stepsCalculated[steps] = ways
        }
//        println(stepsCalculated)
        return ways
    }
}