package com.example.firstapp.dataStructures.heapAsArray

import org.junit.Assert.*
import org.junit.Test

class MinHeapAsArrayTest {
    private val minHeapAsArray = MinHeapAsArray()

    @Test
    fun testGetLeftChildIndex() {
        assertEquals(7, minHeapAsArray.getLeftChildIndex(3))
        assertEquals(11, minHeapAsArray.getLeftChildIndex(5))
    }

    @Test
    fun testGetRightChildIndex() {
        assertEquals(8, minHeapAsArray.getRightChildIndex(3))
        assertEquals(12, minHeapAsArray.getRightChildIndex(5))
    }

    @Test
    fun testGetParentIndex() {
        assertEquals(3, minHeapAsArray.getParentIndex(8))
        assertEquals(3, minHeapAsArray.getParentIndex(7))

        assertEquals(5, minHeapAsArray.getParentIndex(11))
        assertEquals(5, minHeapAsArray.getParentIndex(12))
    }

    @Test
    fun testInsertion() {
        minHeapAsArray.insert(4)
        minHeapAsArray.insert(3)
        minHeapAsArray.insert(2)
        minHeapAsArray.insert(1)
        minHeapAsArray.printArrayWithIndex()
    }

    @Test
    fun testDeletion() {
        minHeapAsArray.insert(4)
        minHeapAsArray.insert(3)
        minHeapAsArray.insert(2)
        minHeapAsArray.insert(1)
        (1..4).forEach {
            var minValue = minHeapAsArray.delete()
            assertEquals(it, minValue)
            println(minValue)
        }
    }

}