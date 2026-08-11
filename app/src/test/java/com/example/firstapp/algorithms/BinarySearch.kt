package com.example.firstapp.algorithms

import org.junit.Test

class BinarySearch {

    private val testArrayEvenSize = listOf(0, 2, 4, 6)
    private val testArrayOddSize = listOf(-2, 0, 2, 4, 6, 8, 10)

    @Test
    fun testBinarySearch() {
        println("-----------------------------------------------")
        val includedItem = 4
        val indexInTestArrayEvenSize = binarySearch(item = includedItem, data = testArrayEvenSize)
        val indexInTestArrayOddSize = binarySearch(item = includedItem, data = testArrayOddSize)
        println("BinarySearch 2 indexInTestArrayEvenSize $indexInTestArrayEvenSize")
        println("BinarySearch 3 indexInTestArrayOddSize $indexInTestArrayOddSize")

        val noIncludedItem = 5
        val noIndexInTestArrayEvenSize =
            binarySearch(item = noIncludedItem, data = testArrayEvenSize)
        println("BinarySearch null noIndexInTestArrayEvenSize $noIndexInTestArrayEvenSize")

        val largerItem = 50
        val largerItemInTestArrayEvenSize =
            binarySearch(item = largerItem, data = testArrayEvenSize)
        println("BinarySearch null largerItemInTestArrayEvenSize $largerItemInTestArrayEvenSize")

        val smallerItem = -50
        val smallerItemInTestArrayEvenSize =
            binarySearch(item = smallerItem, data = testArrayEvenSize)
        println("BinarySearch null smallerItemInTestArrayEvenSize $smallerItemInTestArrayEvenSize")
        println("-----------------------------------------------")
    }

    private fun binarySearch(item: Int, data: List<Int>): Int? {
        var subArrayStartIndex = 0
        var subArrayEndIndex = data.size - 1

        while (subArrayEndIndex >= subArrayStartIndex) {
            val mIndex = (subArrayStartIndex + subArrayEndIndex) / 2
            val middleValue = data[mIndex]
            when {
                item == middleValue -> {
                    return mIndex
                }

                item < middleValue -> {
                    subArrayEndIndex = mIndex - 1
                }

                item > middleValue -> {
                    subArrayStartIndex = mIndex + 1
                }
            }
        }
        return null
    }
}