package com.example.firstapp.codingpatterns.trees

class BinaryTreeNode<T>(
    val value: T,
    var left: BinaryTreeNode<T>? = null,
    var right: BinaryTreeNode<T>? = null
)

fun <T> BinaryTreeNode<T>?.printTree(prefix: String = "", isLeft: Boolean = true) {
    if (this == null) return

    // 1. Process the right child first (appears at the top visually)
    right.printTree(prefix + if (isLeft) "│   " else "    ", false)

    // 2. Print the current node with its branch connectors
    println(prefix + (if (isLeft) "└── " else "┌── ") + value)

    // 3. Process the left child (appears at the bottom visually)
    left.printTree(prefix + if (isLeft) "    " else "│   ", true)
}

