package com.example.firstapp.algoexpert

fun isMonotonic(array: List<Int>): Boolean {
    if (array.size < 2) {
        return true
    } else if (array.size == 2) {
        return true
    }

    var indexToTraverse = 0
    var typeOfChange = IncreasingDecreasing.NONE
    var isMonotonic = true

    run traverseArray@{

        while (indexToTraverse <= array.size - 2) {
            val currentDifference = array[indexToTraverse + 1] - array[indexToTraverse]

            when (typeOfChange) {
                IncreasingDecreasing.INCREASING -> {
                    if (currentDifference < 0) {
                        isMonotonic = false
                        return@traverseArray
                    }
                }
                IncreasingDecreasing.DECREASING -> {
                    if (currentDifference > 0) {
                        isMonotonic = false
                        return@traverseArray
                    }
                }
                else -> {
                    typeOfChange = getIncreasingDecreasingType(difference = currentDifference)
                }
            }
            indexToTraverse++
        }
    }

    return isMonotonic
}

private fun getIncreasingDecreasingType(difference: Int): IncreasingDecreasing = when {
    difference > 0 -> IncreasingDecreasing.INCREASING
    difference < 0 -> IncreasingDecreasing.DECREASING
    else -> IncreasingDecreasing.NONE
}


enum class IncreasingDecreasing() {
    INCREASING, DECREASING, NONE
}

fun testIsMonotonic() {
    val arrayToTest = listOf(-1, -5, -10, -1100, -1100, -1101, -1102, -9001)
//    val arrayToTest = listOf(-1, -5, -10, -1100, -1101, -1102, -9001)
//    val arrayToTest = listOf(1, 5, 10, 1100, 1101, 1102, 9001)
    isMonotonic(array = arrayToTest).also { println(it) }
}