package com.example.firstapp.algoexpert

fun findThreeLargestNumbers(array: List<Int>): List<Int> {
    val negativeInfinite = -1 * Float.POSITIVE_INFINITY.toInt()
    var large3 = negativeInfinite
    var large2 = negativeInfinite
    var large1 = negativeInfinite

    array.forEach { currentInt ->
        if (currentInt >= large3) {
            large1 = large2
            large2 = large3
            large3 = currentInt
            return@forEach
        }
        if (currentInt >= large2) {
            large1 = large2
            large2 = currentInt
            return@forEach
        }
        if (currentInt >= large1) {
            large1 = currentInt
            return@forEach
        }
    }

    return listOf(large1, large2, large3)
}

fun testFindThreeLargestNumbers() {
    val arrayToTest = arrayListOf(141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7)
    findThreeLargestNumbers(array = arrayToTest).forEach { println(it) }
}