package com.example.firstapp.algoexpert

fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
    var leftIndex = 0
    var rightIndex = array.size - 1

    while (leftIndex < rightIndex) {
        val currentLeftValue = array[leftIndex]

        if (currentLeftValue == toMove) {
            var indexFoundToSwap = 0
            run search@{
                for (currentIndex in rightIndex downTo leftIndex + 1) {
                    val currentRightValue = array[currentIndex]
                    if (currentRightValue != toMove) {
                        indexFoundToSwap = currentIndex
                        return@search
                    }
                }
            }

            if (indexFoundToSwap != 0) {
                array[leftIndex] = array[indexFoundToSwap]
                array[indexFoundToSwap] = toMove
                rightIndex = indexFoundToSwap - 1
            }

        }

        leftIndex++
    }

    return array
}

fun testMoveElementToEnd() {
    val testArray = mutableListOf(2, 1, 2, 2, 2, 3, 4, 2)
    val valueToMove = 2
    moveElementToEnd(array = testArray, toMove = valueToMove).forEach { print("$it, ") }
}