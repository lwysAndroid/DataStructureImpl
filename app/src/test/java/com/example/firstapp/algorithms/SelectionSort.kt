package com.example.firstapp.algorithms

import org.junit.Test

class SelectionSort {
    private val unsortedArray = arrayListOf(5, 6, 4, 7, 3, 8, 2, 1, 9)
    private val unsortedArrayWithDuplicatedValues = arrayListOf(5, 3, 6, 4, 7, 9, 3, 8, 2, 5, 1, 9)

    @Test
    fun testSelectionSort() {
        println("-----------------------------------------------")
        println("unsortedArray $unsortedArray")
        val sortedArray = selectionSort(unsortedArray)
        println("sortedArray $sortedArray")
        println("-----------------------------------------------")
        println("unsortedArrayWithDuplicatedValues $unsortedArrayWithDuplicatedValues")
        val sortedArrayWithDuplicatedValues = selectionSort(unsortedArrayWithDuplicatedValues)
        println("sortedArrayWithDuplicatedValues $sortedArrayWithDuplicatedValues")
        println("-----------------------------------------------")
    }

    private fun selectionSort(array: ArrayList<Int>): ArrayList<Int> {
        val sortedArray: ArrayList<Int> = ArrayList()
        val mutableList: MutableList<Int> = array
        array.indices.forEach { _ ->
            val indexOfSmallestElement = fidSmallestIndex(array)
            val smallestElement = mutableList[indexOfSmallestElement]
            mutableList.removeAt(indexOfSmallestElement)
            sortedArray.add(smallestElement)
        }
        return sortedArray
    }

    private fun fidSmallestIndex(array: ArrayList<Int>): Int {
        var smallestIndex = 0
        var sElement = array[smallestIndex]

        array.forEachIndexed { index, curretnElemtn ->
            if (curretnElemtn < sElement) {
                sElement = curretnElemtn
                smallestIndex = index
            }
        }

        return smallestIndex
    }
}