package com.example.firstapp.algoexpert

fun binarySearch(array: List<Int>, target: Int): Int {
    val targetIsInTheRange = target >= array[0] && target <= array[array.size - 1]
    if (targetIsInTheRange.not()) {
        return -1
    }
    var leftIndex = 0
    var rightIndex = array.size - 1
    var midPointIndex: Int = rightIndex / 2
    var targetIndex = -1

    while (leftIndex <= rightIndex) {
        when {
            array[midPointIndex] > target -> {
                rightIndex = midPointIndex - 1
            }
            array[midPointIndex] < target -> {
                leftIndex = midPointIndex + 1
            }
            array[midPointIndex] == target -> {
                targetIndex = midPointIndex
                return targetIndex
            }
        }
        midPointIndex = ((leftIndex + rightIndex) / 2)
    }

    return targetIndex
}

fun testBinarySearch() {
    val arrayToTest = arrayListOf(1, 5, 23, 111)
    val target = 35
    binarySearch(array = arrayToTest, target = target).also { println(it) }
}
