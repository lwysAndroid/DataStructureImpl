package com.example.firstapp.algoexpert

fun isPalindrome(string: String): Boolean {
    var leftIndex = 0
    var rightIndex = string.length - 1

    while (leftIndex <= rightIndex) {
        val leftValue = string[leftIndex]
        val rightValue = string[rightIndex]
        if (leftValue != rightValue) {
            return false
        }
        leftIndex++
        rightIndex--
    }
    return true
}

fun testIsPalindrome() {
    val wordToTest = "abcdcba"
    isPalindrome(string = wordToTest).also { print(it) }
}