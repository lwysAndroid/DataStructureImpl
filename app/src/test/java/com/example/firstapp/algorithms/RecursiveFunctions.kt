package com.example.firstapp.algorithms

import org.junit.Test

class RecursiveFunctions {

    @Test
    fun testRecursiveFunctions() {
        println("-----------------------------------------------")
        countdownRecursiveFunction(start = 8)
        println("-----------------------------------------------")
    }

    private fun countdownRecursiveFunction(start: Int) {
        if (start >= 0) { // Recursive case
            print("$start ")
            countdownRecursiveFunction(start - 1)
        } else { // Base case or Termination condition
            println("")
            return
        }
    }

}