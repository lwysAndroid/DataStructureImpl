package com.example.firstapp.algorithms

import org.junit.Test

class RecursiveFunctions {

    @Test
    fun testCountdownRecursiveFunction() {
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

    @Test
    fun testFactorialRecursiveFunction() {
        println("-----------------------------------------------")
        val factorialOf = 5
        val factorial = factorialRecursiveFunction(factorialOf = factorialOf)
        println("Result $factorialOf! = $factorial")
        println("-----------------------------------------------")
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

    @Test
    fun testSumOfElementsInAnArrayRecursiveFunction() {
        println("-----------------------------------------------")
        val nonEmptyArray = arrayListOf(2, 4, 6, 1)
        val sumNonEmptyArray = sumOfElementsInAnArrayRecursiveFunction(array = nonEmptyArray)
        println("sumNonEmptyArray of $nonEmptyArray is $sumNonEmptyArray")

        val arrayWithOnElement = arrayListOf(5)
        val sumArrayWithOnElement =
            sumOfElementsInAnArrayRecursiveFunction(array = arrayWithOnElement)
        println("sumArrayWithOnElement of $arrayWithOnElement is $sumArrayWithOnElement")

        val emptyArray = arrayListOf<Int>()
        val sumEmptyArray = sumOfElementsInAnArrayRecursiveFunction(array = emptyArray)
        println("sumEmptyArray of $emptyArray is $sumEmptyArray")
        println("-----------------------------------------------")
    }

    private fun sumOfElementsInAnArrayRecursiveFunction(array: ArrayList<Int>): Int {
        if (array.isEmpty()) {
            return -1
        }
        if (array.size == 1) { // Bas case or Termination condition
            return array[0]
        } else { // Recursive case
            val lastElement = array.last()
            val subArray = ArrayList(array.subList(0, array.size - 1))
            return lastElement + sumOfElementsInAnArrayRecursiveFunction(subArray)
        }
    }

    @Test
    fun testCountElementsInAnArrayRecursively() {
        println("-----------------------------------------------")
        val nonEmptyArray = arrayListOf(2, 4, 6, 1)
        val countNonEmptyArray = countElementsInAnArrayRecursively(array = nonEmptyArray)
        println("countNonEmptyArray of $nonEmptyArray is $countNonEmptyArray")

        val arrayWithOnElement = arrayListOf(5)
        val countArrayWithOnElement = countElementsInAnArrayRecursively(array = arrayWithOnElement)
        println("countArrayWithOnElement of $arrayWithOnElement is $countArrayWithOnElement")

        val emptyArray = arrayListOf<Int>()
        val countEmptyArray = countElementsInAnArrayRecursively(array = emptyArray)
        println("countEmptyArray of $emptyArray is $countEmptyArray")
        println("-----------------------------------------------")
    }

    private fun countElementsInAnArrayRecursively(array: ArrayList<Int>): Int {
        if (array.isEmpty()) {
            return 0
        }
        if (array.size == 1) { // Base case or Termination condition
            return 1
        } else { // Recursive case
            val subArray = ArrayList(array.subList(0, array.size - 1))
            return 1 + countElementsInAnArrayRecursively(array = subArray)
        }
    }

    @Test
    fun testFindMaxNumberRecursively() {
        println("-----------------------------------------------")
        val nonEmptyArray = arrayListOf(2, 4, 6, 1)
        val maxNumberNonEmptyArray = findMaxNumberRecursively(array = nonEmptyArray)
        println("maxNumberNonEmptyArray of $nonEmptyArray is $maxNumberNonEmptyArray")

        val arrayWithOnElement = arrayListOf(5)
        val maxNumberArrayWithOnElement = findMaxNumberRecursively(array = arrayWithOnElement)
        println("maxNumberArrayWithOnElement of $arrayWithOnElement is $maxNumberArrayWithOnElement")

        val emptyArray = arrayListOf<Int>()
        val maxNumberEmptyArray = findMaxNumberRecursively(array = emptyArray)
        println("maxNumberEmptyArray of $emptyArray is $maxNumberEmptyArray")
        println("-----------------------------------------------")
    }

    private fun findMaxNumberRecursively(array: ArrayList<Int>): Int {
        if (array.isEmpty()) {
            return -1
        }

        if (array.size == 1) { // Base case of Termination condition
            return array.first()
        } else { // Recursive case
            val lastNumber = array.last()
            val subArray = ArrayList(array.subList(0, array.size - 1))
            val currentMaxNumber = findMaxNumberRecursively(array = subArray)
            return if (currentMaxNumber > lastNumber) {
                currentMaxNumber
            } else {
                lastNumber
            }
        }

    }

}