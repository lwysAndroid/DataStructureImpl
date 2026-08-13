package com.example.firstapp.algorithms

import org.junit.Test

class RecursiveFunctions {

    @Test
    fun testRecursiveFunctions() {
        println("-----------------------------------------------")
        /*---- countdown ----*/
        //countdownRecursiveFunction(start = 8)

        /*---- Factorial ----*/
        val factorialOf = 5
        val factorial = factorialRecursiveFunction(factorialOf = factorialOf)
        println("Result $factorialOf! = $factorial")
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

    private fun factorialRecursiveFunction(factorialOf: Int): Int {
        if (factorialOf < 1) {
            return 0
        }
        return if (factorialOf == 1) { // Base case or Termination condition
            println("return $factorialOf , Termination condition, go back to the topmost of the stack")
            println()
            1
        } else { // Recursive case
            println("$factorialOf * factorialOf(${factorialOf - 1}),  push to the top of the stack")
            val factorial = factorialOf * factorialRecursiveFunction(factorialOf - 1)
            println("pop function of the stack, $factorialOf! = $factorial")
            factorial
        }
    }

}