package com.example.firstapp.algoexpert

fun bubbleSort(array: MutableList<Int>): List<Int> {
    var currentLastIndex = array.size - 2
    while (currentLastIndex >= 0) {

        (0..currentLastIndex).forEach {
            val currentValue = array[it]
            val nextValue = array[it + 1]
            if (currentValue > nextValue) {
                array[it + 1] = currentValue
                array[it] = nextValue
            }
        }
        currentLastIndex--
    }

    return array
}

fun testBubbleSort() {
    val arrayToTest = arrayListOf(8, 5, 2, 9, 5, 6, 3)
    bubbleSort(array = arrayToTest).forEach { print("$it,") }
}