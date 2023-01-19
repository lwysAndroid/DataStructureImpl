package com.example.firstapp.dataStructures.binaryTree

/**
 * Having a Binary Search Tree this algorithm will traverse all the values
 * in ascending order
 * @param rootNode as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> inOrder(rootNode: BinaryTreeNode<T>?, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    if (rootNode != null) {
        inOrder(rootNode = rootNode.leftChild, doOnVisit = doOnVisit)
        doOnVisit(rootNode)
        inOrder(rootNode = rootNode.rightChild, doOnVisit = doOnVisit)
    }
}

/**
 * This algorithm will print first the root node
 * @param rootNode as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> preOrder(rootNode: BinaryTreeNode<T>?, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    if (rootNode != null) {
        doOnVisit(rootNode)
        preOrder(rootNode = rootNode.leftChild, doOnVisit = doOnVisit)
        preOrder(rootNode = rootNode.rightChild, doOnVisit = doOnVisit)
    }
}

/**
 * This algorithm will print to the last the root node
 * @param rootNode as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> postOrder(rootNode: BinaryTreeNode<T>?, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    if (rootNode != null) {
        postOrder(rootNode = rootNode.leftChild, doOnVisit = doOnVisit)
        postOrder(rootNode = rootNode.rightChild, doOnVisit = doOnVisit)
        doOnVisit(rootNode)
    }
}