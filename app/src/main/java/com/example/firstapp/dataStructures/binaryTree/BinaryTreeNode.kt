package com.example.firstapp.dataStructures.binaryTree

class BinaryTreeNode<T>(var value: T) {
    var leftChild: BinaryTreeNode<T>? = null
    var rightChild: BinaryTreeNode<T>? = null
    var parent: BinaryTreeNode<T>? = null
}