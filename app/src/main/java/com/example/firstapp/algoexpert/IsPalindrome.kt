package com.example.firstapp.algoexpert

fun isPalindrome(string: String): Boolean {
    val charArray = string.toCharArray()
    var leftIndex = 0
    var rightIndex = charArray.size - 1

    while (leftIndex <= rightIndex) {
        val leftValue = charArray[leftIndex]
        val rightValue = charArray[rightIndex]
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