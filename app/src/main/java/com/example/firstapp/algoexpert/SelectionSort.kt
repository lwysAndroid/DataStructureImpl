package com.example.firstapp.algoexpert

fun selectionSort(array: MutableList<Int>): List<Int> {

    var currentMinIndex = 0
    var currentMinValue = array[0]
    var currentStart = 0
    val lastIndex = array.size - 1

    while (currentStart < lastIndex) {

        (currentStart..lastIndex).forEach { currentIndex ->
            val currentValue = array[currentIndex]
            if (currentValue < currentMinValue) {
                currentMinValue = currentValue
                currentMinIndex = currentIndex
            }
        }

        if (currentStart != currentMinIndex) {
            val temStart = array[currentStart]
            array[currentStart] = currentMinValue
            array[currentMinIndex] = temStart
        }

        currentStart++
        currentMinValue = array[currentStart]
        currentMinIndex = currentStart
    }


    return array
}


fun testSelectionSort() {
    val arrayToTest = arrayListOf(1, 2, 3)
    selectionSort(array = arrayToTest).forEach { print("$it,") }
}