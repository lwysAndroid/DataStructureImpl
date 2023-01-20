package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.inOrder
import org.junit.Assert.assertEquals
import org.junit.Test

class MinHeapTest {

    /*

                     Start
                       4
                50          7
             55    90     87  (2)//added

    After Insertion of "2"
                       2
                50          4
             55    90     87  7
    */
    @Test
    fun testInsertion() {
        val minHeap = MinHeap()
        with(minHeap){
            insert(4)
            insert(50)
            insert(7)
            insert(55)
            insert(90)
            insert(87)
            insert(2)
        }
        val heapValuesList = ArrayList<Int>()
        inOrder(minHeap.root) { heapValuesList.add(it.value) }
        val arraySample = arrayListOf(55, 50, 90, 2, 87, 4, 7)
        assertEquals(arraySample.size, heapValuesList.size)
        arraySample.forEachIndexed { index, value ->
            assertEquals(value, heapValuesList[index])
        }
    }

}