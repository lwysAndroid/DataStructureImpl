package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.inOrder
import org.junit.Assert.assertEquals
import org.junit.Test

class MinHeapTest {

    /*
    After Insertion of "2"
                       2
                50          4
             55    90     87  7
    */
    @Test
    fun testInsertion() {
        val minHeap = MinHeap()
        minHeap.insert(4)
        minHeap.insert(50)
        minHeap.insert(7)
        minHeap.insert(55)
        minHeap.insert(90)
        minHeap.insert(87)
        minHeap.insert(2)
        val heapValuesList = ArrayList<Int>()
        inOrder(minHeap.root) { heapValuesList.add(it.value) }
        val arraySample = arrayListOf(55, 50, 90, 2, 87, 4, 7)
        assertEquals(arraySample.size, heapValuesList.size)
        arraySample.forEachIndexed { index, value ->
            assertEquals(value, heapValuesList[index])
        }
    }

}