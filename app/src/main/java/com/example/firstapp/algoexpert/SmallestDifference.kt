package com.example.firstapp.algoexpert

import kotlin.math.abs

fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {
    arrayOne.sort()
    arrayTwo.sort()
    var indexArrayOneMinDif = 0
    var indexArrayTwoMinDif = 0
    var minDif = Float.POSITIVE_INFINITY.toInt()

    var indexArrayOne = 0
    var indexArrayTwo = 0

    val pairOfMinDifference = mutableListOf<Int>()

    while (indexArrayOne <= arrayOne.size - 1 && indexArrayTwo <= arrayTwo.size - 1 && minDif != 0) {
        val currentValueArrayOne = arrayOne[indexArrayOne]
        val currentValueArrayTwo = arrayTwo[indexArrayTwo]
        val currentDifference = currentValueArrayTwo - currentValueArrayOne
        val currentDifferenceAbsolute = abs(currentDifference)

        if (currentDifferenceAbsolute < minDif) {
            minDif = currentDifferenceAbsolute
            indexArrayOneMinDif = indexArrayOne
            indexArrayTwoMinDif = indexArrayTwo
        }

        if (currentDifference > 0) {
            indexArrayOne++
        } else if (currentDifference < 0) {
            indexArrayTwo++
        }
    }

    pairOfMinDifference.add(arrayOne[indexArrayOneMinDif])
    pairOfMinDifference.add(arrayTwo[indexArrayTwoMinDif])

    return pairOfMinDifference
}

fun testSmallestDifference() {
    val testArrayOne = mutableListOf(-1, 5, 10, 20, 28, 3)
    val testArrayTwo = mutableListOf(26, 134, 135, 15, 17)
    smallestDifference(arrayOne = testArrayOne, arrayTwo = testArrayTwo).forEach {
        print("$it, ")
    }
}