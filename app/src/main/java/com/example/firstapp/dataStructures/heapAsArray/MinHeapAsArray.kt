package com.example.firstapp.dataStructures.heapAsArray

import java.lang.Float.POSITIVE_INFINITY

class MinHeapAsArray {

    private val heapArray = arrayListOf<Int>()

    fun insert(value: Int) {
        heapArray.add(value)

        var currentIndex = heapArray.lastIndex
        var parentIndex = getParentIndex(currentIndex)

        while (currentIndex > 0 && (heapArray[currentIndex] < heapArray[parentIndex])) {
            swapChildWithParent(childIndex = currentIndex, parentIndex = parentIndex)
            currentIndex = parentIndex
            parentIndex = getParentIndex(currentIndex)
        }
    }

    fun delete(): Int? {
        if (heapArray.isEmpty()) {
            return null
        }
        if (heapArray.size == 1) {
            return heapArray.removeLast()
        }
        val minValue = heapArray[0]
        val lastValue = heapArray.removeLast()
        heapArray[0] = lastValue
        var currentIndex = 0
        var childIndexToSwap = findIndexToMoveDownTheParent(currentIndex)

        while (childIndexToSwap != null) {
            swapChildWithParent(childIndex = childIndexToSwap, parentIndex = currentIndex)
            currentIndex = childIndexToSwap
            childIndexToSwap = findIndexToMoveDownTheParent(currentIndex)
        }

        return minValue
    }

    private fun findIndexToMoveDownTheParent(parentIndex: Int): Int? {
        val leftChildIndex = getLeftChildIndex(parentIndex)
        val rightChildIndex = getRightChildIndex(parentIndex)

        val leftChildValue: Int = if (leftChildIndex <= heapArray.lastIndex) {
            heapArray[leftChildIndex]
        } else {
            POSITIVE_INFINITY.toInt()
        }

        val rightChildValue = if (rightChildIndex <= heapArray.lastIndex) {
            heapArray[rightChildIndex]
        } else {
            POSITIVE_INFINITY.toInt()
        }

        val parentValue = heapArray[parentIndex]

        val leftChildIsSmallerThanParent = leftChildValue < parentValue
        val rightChildIsSmallerThanParent = rightChildValue < parentValue

        var indexToSwap: Int? = null
        if (leftChildIsSmallerThanParent && rightChildIsSmallerThanParent) {
            indexToSwap = if (rightChildValue < leftChildValue) {
                rightChildIndex
            } else {
                leftChildIndex
            }
        } else if (leftChildIsSmallerThanParent) {
            indexToSwap = leftChildIndex
        } else if (rightChildIsSmallerThanParent) {
            indexToSwap = rightChildIndex
        }

        return indexToSwap
    }

    fun printArrayWithIndex() {
        heapArray.forEachIndexed { index, value ->
            println("index: $index, value: $value")
        }
    }

    private fun swapChildWithParent(childIndex: Int, parentIndex: Int) {
        val parentValue = heapArray[parentIndex]
        heapArray[parentIndex] = heapArray[childIndex]
        heapArray[childIndex] = parentValue
    }

    fun getLeftChildIndex(index: Int): Int {
        return (index * 2) + 1
    }

    fun getRightChildIndex(index: Int): Int {
        return (index * 2) + 2
    }

    fun getParentIndex(index: Int): Int {
        return (index - 1) / 2
    }

}