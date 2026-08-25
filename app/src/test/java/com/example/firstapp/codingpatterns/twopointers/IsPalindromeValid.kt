package com.example.firstapp.codingpatterns.twopointers

import com.example.firstapp.printHyphensSeparation
import org.junit.Test
import java.util.Locale

class IsPalindromeValid {

    @Test
    fun testIsPalindromeValid() {
        printHyphensSeparation()
        printTestIsPalindromeValid(input = "A dog! a panic in a pagoda")
//        printTestIsPalindromeValid(input = "abc123")
//        printTestIsPalindromeValid(input = "racecar")

        // Test cases
//        printTestIsPalindromeValid(input = "")
//        printTestIsPalindromeValid(input = "a")
//        printTestIsPalindromeValid(input = "aa")
//        printTestIsPalindromeValid(input = "ab")
//        printTestIsPalindromeValid(input = "|, (?)")
//        printTestIsPalindromeValid(input = "12.02.2021")
//        printTestIsPalindromeValid(input = "21.02.2021")
        printTestIsPalindromeValid(input = "hello, world!")
    }

    private fun printTestIsPalindromeValid(input: String) {
        val isPalindrome = isPalindromeValid(input = input)
        val message = "Is Palindrome: $isPalindrome. $input"
        println(message)
        printHyphensSeparation()
    }

    private fun isPalindromeValid(input: String): Boolean {
        val inputCleaned = input.filter { it.isLetterOrDigit() }.uppercase(Locale.getDefault())
        var leftIndex = 0
        var rightIndex = inputCleaned.length - 1

        while (leftIndex <= rightIndex) {
            val leftCharacter = inputCleaned[leftIndex]
            val rightCharacter = inputCleaned[rightIndex]
            if (leftCharacter == rightCharacter) {
                leftIndex++
                rightIndex--
            } else {
                return false
            }
        }
        return true
    }
}
