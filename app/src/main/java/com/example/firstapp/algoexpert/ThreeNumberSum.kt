package com.example.firstapp.algoexpert

fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    array.sort()
    val hashMapInt = HashMap<Int, Int>()
    array.forEachIndexed { index, value ->
        hashMapInt[value] = index
    }
    val listOfTriples = mutableListOf<List<Int>>()
    var currentIndex = 0
    var currentValue = array[currentIndex]
    while (currentIndex <= array.size - 3) {

        (currentIndex + 1 until array.size).forEach { currentIndexFor ->
            val currentIntValue = array[currentIndexFor]
            val currentSum = currentValue + currentIntValue
            val currentComplement = targetSum - currentSum

            hashMapInt.get(key = currentComplement)?.let { index ->
                if (index > currentIndex && index > currentIndexFor) {
                    listOfTriples.add(
                        element = mutableListOf(
                            currentValue,
                            currentIntValue,
                            currentComplement
                        )
                    )
                }
            }
        }

        currentIndex++
        currentValue = array[currentIndex]
    }

    return listOfTriples
}


fun threeNumberSumTwoIndex(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    array.sort()
    val listOfTriples = mutableListOf<List<Int>>()

    (0 until array.size - 2).forEach { currentIndexFor ->
        var currentValueFor = array[currentIndexFor]
        var leftIndex = currentIndexFor + 1
        var rightIndex = array.size - 1

        while (leftIndex < rightIndex) {
            val currentSum = currentValueFor + array[leftIndex] + array[rightIndex]
            if (currentSum == targetSum) {
                listOfTriples.add(listOf(currentValueFor, array[leftIndex], array[rightIndex]))
                leftIndex++
                rightIndex--
            } else if (currentSum < targetSum) {
                leftIndex++
            } else if (currentSum > targetSum) {
                rightIndex--
            }
        }

    }
    return listOfTriples
}

fun testThreeNumberSum() {
    val arrayToTest = mutableListOf(12, 3, 1, 2, -6, 5, -8, 6)
    val targetSum = 0
    threeNumberSumTwoIndex(array = arrayToTest, targetSum = targetSum).forEach { sumList ->
        sumList.forEach { print("$it, ") }
        println()
    }
}