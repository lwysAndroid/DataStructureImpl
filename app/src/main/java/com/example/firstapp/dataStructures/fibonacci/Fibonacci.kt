package com.example.firstapp.dataStructures.fibonacci

import kotlin.system.measureTimeMillis

fun main() {

    val positionRequired = 30

    /**
     * Recursion plus memoization is the same of dynamic programing
     */
    val timeFibMemoization = measureTimeMillis {
        fib(positionRequired = positionRequired).also { println(it) }
    }
    println("timeFibMemoization: $timeFibMemoization")
    println()

    val timeFibRecursion = measureTimeMillis {
        fibRecursion(positionRequired = positionRequired).also { println(it) }
    }
    println("timeFibRecursion: $timeFibRecursion")

}

/**
 * time complexity of O(N)
 * @param positionRequired position between 0 to n
 */
fun fib(
    positionRequired: Int,
    arrayWithPreviousValues: Array<Int> = Array(positionRequired + 1) { 0 }
): Int {
    if (positionRequired == 0 || positionRequired == 1) {
        return 1
    }
    if (arrayWithPreviousValues[positionRequired] == 0) {
        arrayWithPreviousValues[positionRequired] =
            fib(
                positionRequired = positionRequired - 1,
                arrayWithPreviousValues = arrayWithPreviousValues
            ) +
                    fib(
                        positionRequired = positionRequired - 2,
                        arrayWithPreviousValues = arrayWithPreviousValues
                    )
    }
    return arrayWithPreviousValues[positionRequired]
}

/**
 * time complexity of O(2^N)
 * This is an implementation without using memoization so it will perform the same
 * operation multiple times so it is a bad implementation of the Fibonacci function
 */
fun fibRecursion(positionRequired: Int): Int {

    return if (positionRequired <= 1) {
        1
    } else {
        (fibRecursion(positionRequired - 1) + fibRecursion(positionRequired - 2))
    }
}
