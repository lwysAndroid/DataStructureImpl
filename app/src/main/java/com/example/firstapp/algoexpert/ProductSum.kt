package com.example.firstapp.algoexpert

fun productSum(array: List<*>, level: Int = 1): Int {
    var sum = 0
    array.forEach {
        when (it) {
            is Int -> {
                sum += (it * level)
            }
            else -> {
                sum += (level * productSum(array = it as List<*>, level = level + 1))
            }
        }
    }

    return sum
}

fun testProductSum() {
    val testList = listOf(5, 2, listOf(7, -1), 3, listOf(6, listOf(-13, 8), 4))
    productSum(array = testList).also { println("sum: $it") }
}