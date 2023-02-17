package com.example.firstapp.algoexpert

fun longestPeak(array: List<Int>): Int {
    if (array.size < 3) {
        return 0
    }

    var longestPeak = 0
    var possibleLongestPeak = 0
    var currentIndex = 0
    var lastWasEqual = false

    while (currentIndex <= array.size - 2) {
        val currentValue = array[currentIndex]
        val nextValue = array[currentIndex + 1]
        if (nextValue > currentValue) {
            possibleLongestPeak++
            lastWasEqual = false
        } else {
            if (currentValue != nextValue) {
                if (lastWasEqual) {
                    possibleLongestPeak = 0
                } else {
                    if (possibleLongestPeak >= 1) {
                        possibleLongestPeak += 2
                        if (possibleLongestPeak > longestPeak) {
                            longestPeak = possibleLongestPeak
                        }
                        possibleLongestPeak = 0
                    }
                }
            } else {
                lastWasEqual = true
                possibleLongestPeak++
            }
        }
        currentIndex++
    }
    lastWasEqual = false
    possibleLongestPeak = 0
    currentIndex = array.size - 1
    while (currentIndex >= 1) {
        val currentValue = array[currentIndex]
        val nextValue = array[currentIndex - 1]
        if (nextValue > currentValue) {
            lastWasEqual = false
            possibleLongestPeak++
        } else {
            if (currentValue != nextValue) {
                if (lastWasEqual) {
                    possibleLongestPeak = 0
                } else {
                    if (possibleLongestPeak >= 1) {
                        possibleLongestPeak += 2
                        if (possibleLongestPeak > longestPeak) {
                            longestPeak = possibleLongestPeak
                        }
                        possibleLongestPeak = 0
                    }
                }
            } else {
                lastWasEqual = true
                possibleLongestPeak++
            }
        }
        currentIndex--
    }


    return longestPeak
}

fun testLongestPeak() {
//    val arrayToTest = listOf(1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3)
//    val arrayToTest = listOf(1, 3, 2)
//    val arrayToTest = listOf(5, 4, 3, 2, 1, 2, 10, 12)
//    val arrayToTest = listOf(1, 2, 3, 3, 2, 1)
//    val arrayToTest = listOf(1, 1, 3, 2, 1)
    val arrayToTest = listOf(1, 2, 3, 2, 1, 1)
    longestPeak(array = arrayToTest).also { println(it) }
}