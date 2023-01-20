package com.example.firstapp.dataStructures.binaryTree

/**
 * Having a Binary Search Tree this algorithm will traverse all the values
 * in ascending order
 * @param visited as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> inOrder(visited: BinaryTreeNode<T>?, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    if (visited != null) {
        inOrder(visited = visited.leftChild, doOnVisit = doOnVisit)
        doOnVisit(visited)
        inOrder(visited = visited.rightChild, doOnVisit = doOnVisit)
    }
}

/**
 * Having a Binary Search Tree this algorithm will traverse all the values
 * in ascending order
 * @param visited as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> inOrderOption(visited: BinaryTreeNode<T>, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    visited.leftChild?.let { inOrderOption(visited = it, doOnVisit = doOnVisit) }
    doOnVisit(visited)
    visited.rightChild?.let { inOrderOption(visited = it, doOnVisit = doOnVisit) }
}

/**
 * This algorithm will print first the root node
 * @param visited as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> preOrder(visited: BinaryTreeNode<T>?, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    if (visited != null) {
        doOnVisit(visited)
        preOrder(visited = visited.leftChild, doOnVisit = doOnVisit)
        preOrder(visited = visited.rightChild, doOnVisit = doOnVisit)
    }
}

/**
 * This algorithm will print to the last the root node
 * @param visited as the name says it is the root node
 * @param doOnVisit lambda function that perform the desired operation in the node visited
 */
fun <T> postOrder(visited: BinaryTreeNode<T>?, doOnVisit: (BinaryTreeNode<T>) -> Unit) {
    if (visited != null) {
        postOrder(visited = visited.leftChild, doOnVisit = doOnVisit)
        postOrder(visited = visited.rightChild, doOnVisit = doOnVisit)
        doOnVisit(visited)
    }
}