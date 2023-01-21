package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.BinaryTreeNode

fun main() {
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
    minHeap.extract().also { println(it) }
    minHeap.extract().also { println(it) }
    minHeap.printWithLevels()
}

fun getFilledHeapInsertion(): BinaryTreeNode<Int> {
    return headHeapInsertion
}

fun getFilledHeapExtract(): BinaryTreeNode<Int> {
    return headHeapExtract
}



