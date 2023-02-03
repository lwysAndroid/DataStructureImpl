package com.example.firstapp.algoexpert

fun insertionSort(array: MutableList<Int>): List<Int> {
    var temIndex = 1
    var temVal: Int

    while (temIndex < array.size) {
        temVal = array[temIndex]

        var currentSwaps = 0
        run lit@{
            (temIndex - 1 downTo 0).forEach { currentIndex ->
                val currentValue = array[currentIndex]
                if (currentValue > temVal) {
                    array[temIndex - currentSwaps] = currentValue
                    currentSwaps++
                    array[temIndex - currentSwaps] = temVal
                } else {
                    return@lit
                }
            }
        }

        temIndex++
    }

    return array
}

fun testInsertionSort() {
//    val arrayToTest = arrayListOf(8, 5, 2, 9, 5, 6, 3)
    val arrayToTest = arrayListOf(4, 2, 7, 1, 3)
    insertionSort(array = arrayToTest).forEach { print("$it,") }
}