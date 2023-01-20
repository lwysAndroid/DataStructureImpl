package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.BinaryTreeNode

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
private val node55 = BinaryTreeNode(value = 55)
private val node90 = BinaryTreeNode(value = 90)
private val node50 = BinaryTreeNode(value = 50)
    .apply {
        leftChild = node55
        rightChild = node90
    }

/*right side*/
private val node87 = BinaryTreeNode(value = 87)
private val node7 = BinaryTreeNode(value = 7)
    .apply { leftChild = node87 }

/*root*/
private val node4 = BinaryTreeNode(value = 4)
    .apply {
        leftChild = node50
        rightChild = node7
    }

val headHeapInsertion = node4
//endregion