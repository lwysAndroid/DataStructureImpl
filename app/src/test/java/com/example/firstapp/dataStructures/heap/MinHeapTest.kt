package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.inOrder
import com.example.firstapp.dataStructures.binaryTree.inOrderOption
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
        with(minHeap) {
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

    /*
                      Start
                       1
                50          23
             88    90     32  74
           89

              After Extraction of 1
                       23
                50          32
             88    90     89  74
*/
    @Test
    fun testExtraction() {
        val minHeap = MinHeap()
        with(minHeap) {
            insert(1)
            insert(50)
            insert(23)
            insert(88)
            insert(90)
            insert(32)
            insert(74)
            insert(89)
        }
        minHeap.extract()
        val heapValuesList = ArrayList<Int>()
        inOrderOption(minHeap.root!!) { heapValuesList.add(it.value) }
        val arraySample = arrayListOf(88, 50, 90, 23, 89, 32, 74)
        assertEquals(arraySample.size, heapValuesList.size)
        arraySample.forEachIndexed { index, value ->
            assertEquals(value, heapValuesList[index])
        }
    }

    /*
                     Start
                      1
               50          23
            88    90     32  74
          89

             After Extraction of 1
                      32
               50          74
            88    90     89
*/
    @Test
    fun testExtractionTwo() {
        val minHeap = MinHeap()
        with(minHeap) {
            insert(1)
            insert(50)
            insert(23)
            insert(88)
            insert(90)
            insert(32)
            insert(74)
            insert(89)
        }
        minHeap.extract()
        minHeap.extract()
        val heapValuesList = ArrayList<Int>()
        inOrderOption(minHeap.root!!) { heapValuesList.add(it.value) }
        val arraySample = arrayListOf(88, 50, 90, 32, 89, 74)
        assertEquals(arraySample.size, heapValuesList.size)
        arraySample.forEachIndexed { index, value ->
            assertEquals(value, heapValuesList[index])
        }
    }

}