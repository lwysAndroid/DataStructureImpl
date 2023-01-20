package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.BinaryTreeNode

class MinHeap {
    var root: BinaryTreeNode<Int>? = null

    fun insert(value: Int) {
        val insertedNode = BinaryTreeNode(value = value)
        if (root == null) {
            root = insertedNode
            return
        }
        addNodeToTheBottom(root = root!!, insertedNode = insertedNode)
        bubbleUpNodeInserted(insertedNode = insertedNode)
    }

    private fun bubbleUpNodeInserted(insertedNode: BinaryTreeNode<Int>) {
        var parent = insertedNode.parent
        while (parent != null && insertedNode.value < parent.value) {
            val parentLeftChild = parent.leftChild
            val parentRightChild = parent.rightChild
            val parentParent = parent.parent

            val insertedLeftChild = insertedNode.leftChild
            val insertedRightChild = insertedNode.rightChild

            if (parentParent?.rightChild == parent) {
                parentParent.rightChild = insertedNode
            } else {
                parentParent?.leftChild = insertedNode
            }
            insertedNode.parent = parentParent


            if (parentRightChild == insertedNode) {
                insertedNode.leftChild = parentLeftChild
                parentLeftChild?.parent = insertedNode
                insertedNode.rightChild = parent
            } else {
                insertedNode.leftChild = parent
            }

            parent.leftChild = insertedLeftChild
            parent.rightChild = insertedRightChild
            insertedLeftChild?.parent = parent
            insertedRightChild?.parent = parent

            parent = parentParent
            if (parent == null) {
                root = insertedNode
            }

        }

    }

    private fun addNodeToTheBottom(root: BinaryTreeNode<Int>, insertedNode: BinaryTreeNode<Int>) {
        val levelToInsertTheNewNode = getDeeperLevelRight(root)
        findNodeToAddTheBottomNode(root, currentLevel = 0, desiredLevel = levelToInsertTheNewNode)
        insertedNode.parent = nodeWhereShouldBeAddedTheNewNode
        if (nodeWhereShouldBeAddedTheNewNode!!.leftChild == null) {
            nodeWhereShouldBeAddedTheNewNode!!.leftChild = insertedNode
        } else {
            nodeWhereShouldBeAddedTheNewNode!!.rightChild = insertedNode
        }
    }

    var nodeWhereShouldBeAddedTheNewNode: BinaryTreeNode<Int>? = null

    private fun findNodeToAddTheBottomNode(
        root: BinaryTreeNode<Int>?,
        currentLevel: Int,
        desiredLevel: Int
    ) {
        if (currentLevel == 0) {
            nodeWhereShouldBeAddedTheNewNode = null
        }
        if (root != null && nodeWhereShouldBeAddedTheNewNode == null) {
            if (currentLevel == desiredLevel && (root.leftChild == null || root.rightChild == null)) {
                nodeWhereShouldBeAddedTheNewNode = root
            }
            val newLevel = currentLevel + 1
            findNodeToAddTheBottomNode(
                root.leftChild,
                currentLevel = newLevel,
                desiredLevel = desiredLevel
            )
            findNodeToAddTheBottomNode(
                root.rightChild,
                currentLevel = newLevel,
                desiredLevel = desiredLevel
            )
        }
    }

    fun printWithLevels() {
        printWithLevels(root = root, currentLevel = 0)
    }

    private fun printWithLevels(
        root: BinaryTreeNode<Int>?,
        currentLevel: Int
    ) {
        if (root != null) {
            val newLevel = currentLevel + 1
            printWithLevels(
                root.leftChild,
                currentLevel = newLevel,
            )
            println("value: ${root.value} parentValue: ${root.parent?.value} level: $currentLevel")
            printWithLevels(
                root.rightChild,
                currentLevel = newLevel,
            )
        }
    }

    private fun getDeeperLevelRight(root: BinaryTreeNode<Int>): Int {
        var deeperLevel = 0
        var currentNode = root
        while (currentNode.rightChild != null) {
            deeperLevel++
            currentNode = currentNode.rightChild!!
        }
        return deeperLevel
    }

}