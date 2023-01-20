package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.BinaryTreeNode

fun main() {
    val minHeap = MinHeap()
    minHeap.insert(4)
    minHeap.insert(50)
    minHeap.insert(7)
    minHeap.insert(55)
    minHeap.insert(90)
    minHeap.insert(87)
    minHeap.insert(2)
    minHeap.printWithLevels()
}

fun getFilledHeap(): BinaryTreeNode<Int> {
    return node4
}

//region Heap example
/*
                      Start
                       4
                50          7
             55    90     87  (2)//added

              After Insertion of 2
                       2
                50          4
             55    90     87  7
*/
/*left side*/
val node55 = BinaryTreeNode(value = 55)
val node90 = BinaryTreeNode(value = 90)
val node50 = BinaryTreeNode(value = 50)
    .apply {
        leftChild = node55
        rightChild = node90
    }

/*right side*/
val node87 = BinaryTreeNode(value = 87)
val node7 = BinaryTreeNode(value = 7)
    .apply { leftChild = node87 }

/*root*/
val node4 = BinaryTreeNode(value = 4)
    .apply {
        leftChild = node50
        rightChild = node7
    }
//endregion

