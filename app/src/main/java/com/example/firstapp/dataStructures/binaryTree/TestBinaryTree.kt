package com.example.firstapp.dataStructures.binaryTree

fun main() {
    val rootNode = getBinarySearchTree()
    println("inOrder --------------------")
    inOrder(rootNode = rootNode, doOnVisit = ::doOnVisit)
    println()

    println("preOrder --------------------")
    preOrder(rootNode = rootNode, doOnVisit = ::doOnVisit)
    println()

    println("postOrder --------------------")
    postOrder(rootNode = rootNode, doOnVisit = ::doOnVisit)
}

fun doOnVisit(node: BinaryTreeNode<Int>) {
    print("${node.value} ")
}

fun getBinarySearchTree(): BinaryTreeNode<Int> {
    /*left side*/
    val node4 = BinaryTreeNode(value = 4)
    val node7 = BinaryTreeNode(value = 7)
    val node6 = BinaryTreeNode(value = 6)
        .apply {
            leftChild = node4
            rightChild = node7
        }
    val node1 = BinaryTreeNode(value = 1)
    val node3 = BinaryTreeNode(value = 3)
        .apply {
            leftChild = node1
            rightChild = node6
        }
    /*right side*/
    val node13 = BinaryTreeNode(value = 13)
    val node14 = BinaryTreeNode(value = 14)
        .apply { leftChild = node13 }
    val node10 = BinaryTreeNode(value = 10)
        .apply { rightChild = node14 }
    /*root*/
    val node8 = BinaryTreeNode(value = 8)
        .apply {
            leftChild = node3
            rightChild = node10
        }
    return node8
}