package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.BinaryTreeNode

//region Heap example
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
/*left side*/
private val node89 = BinaryTreeNode(value = 89)
private val node88 = BinaryTreeNode(value = 88).apply { leftChild= node89 }
private val node90 = BinaryTreeNode(value = 90)
private val node50 = BinaryTreeNode(value = 50)
    .apply {
        leftChild = node88
        rightChild = node90
    }

/*right side*/
private val node32 = BinaryTreeNode(value = 32)
private val node74 = BinaryTreeNode(value = 74)
private val node23 = BinaryTreeNode(value = 23)
    .apply {
        leftChild = node32
        rightChild= node74
    }

/*root*/
private val node1 = BinaryTreeNode(value = 1)
    .apply {
        leftChild = node50
        rightChild = node23
    }

val headHeapExtract = node1
//endregion