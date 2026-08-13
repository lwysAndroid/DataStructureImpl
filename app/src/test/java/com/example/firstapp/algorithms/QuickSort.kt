package com.example.firstapp.algorithms

import com.example.firstapp.algorithms.SelectionSort.Companion.unsortedArray
import com.example.firstapp.algorithms.SelectionSort.Companion.unsortedArrayWithDuplicatedValues
import org.junit.Test

class QuickSort {

    @Test
    fun testQuickSort() {
        println("-----------------------------------------------")
        println("unsortedArray $unsortedArray")
        val sortedArray = quickSort(unsortedArray)
        println("sortedArray $sortedArray")
        println("-----------------------------------------------")
        println("unsortedArrayWithDuplicatedValues $unsortedArrayWithDuplicatedValues")
        val sortedArrayWithDuplicatedValues = quickSort(unsortedArrayWithDuplicatedValues)
        println("sortedArrayWithDuplicatedValues $sortedArrayWithDuplicatedValues")
        println("-----------------------------------------------")
    }

    private fun quickSort(array: ArrayList<Int>): ArrayList<Int> {

        if (array.size <= 1) { // Base case or Termination condition
            return array
        } else { // Recursive case

            val pivot = array.first()
            val arrayWithSmallerOnesThanPivot: ArrayList<Int> = arrayListOf()
            val arrayWithLargerOnesThanPivot: ArrayList<Int> = arrayListOf()

            (1 until array.size).forEach { index ->
                val currentValue = array[index]
                if (currentValue < pivot) {
                    arrayWithSmallerOnesThanPivot.add(currentValue)
                } else {
                    arrayWithLargerOnesThanPivot.add(currentValue)
                }
            }

            val arrayWithSmallerOnesOrdered = quickSort(array = arrayWithSmallerOnesThanPivot)
            val arrayWithLargerOnesOrdered = quickSort(array = arrayWithLargerOnesThanPivot)

            return ArrayList(arrayWithSmallerOnesOrdered + pivot + arrayWithLargerOnesOrdered)
        }
    }
}