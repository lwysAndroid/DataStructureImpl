package com.example.firstapp.algoexpert

fun getNthFib(n: Int, fibArray: MutableList<Int>? = null): Int {
    if (n == 1) {
        return 0
    }
    if (n == 2) {
        return 1
    }

    val currentArray: MutableList<Int> = fibArray ?: MutableList(n) { 0 }
    val currentPosition = n - 1
    if (currentArray[currentPosition] == 0) {
        currentArray[currentPosition] =
            getNthFib(n - 1, fibArray = currentArray) + getNthFib(n - 2, fibArray = currentArray)
    }

    return currentArray[currentPosition]
}


fun testGetNthFib() {
    getNthFib(6)
}