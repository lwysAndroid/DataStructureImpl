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
            parent = swapParentWithChildNodes(parent = parent, child = insertedNode)
            if (parent == null) {
                root = insertedNode
            }
        }
    }

    /**
     * Swap the parent with one of its children and return the new parent of the children after the
     * swapping
     *
     * @param parent parent node
     * @param child child node
     * @return return the new parent node of the child swapped, it returns null if the child became
     * the head of the heap
     */
    private fun swapParentWithChildNodes(
        parent: BinaryTreeNode<Int>,
        child: BinaryTreeNode<Int>
    ): BinaryTreeNode<Int>? {
        val parentLeftChild = parent.leftChild
        val parentRightChild = parent.rightChild
        val parentParent = parent.parent

        val childLeftChild = child.leftChild
        val childRightChild = child.rightChild

        if (parentParent?.rightChild == parent) {
            parentParent.rightChild = child
        } else {
            parentParent?.leftChild = child
        }
        child.parent = parentParent

        if (parentRightChild == child) {
            child.rightChild = parent
            child.leftChild = parentLeftChild
            parentLeftChild?.parent = child
        } else {
            child.leftChild = parent
            child.rightChild = parentRightChild
            parentRightChild?.parent = child
        }

        parent.leftChild = childLeftChild
        parent.rightChild = childRightChild
        childLeftChild?.parent = parent
        childRightChild?.parent = parent
        return parentParent
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