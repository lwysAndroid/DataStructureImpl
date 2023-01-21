package com.example.firstapp.dataStructures.heap

import com.example.firstapp.dataStructures.binaryTree.BinaryTreeNode
import java.lang.Float.POSITIVE_INFINITY

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

    fun extract(): Int {
        if (root == null) {
            throw Exception()
        }

        if (root?.leftChild == null && root?.rightChild == null) {
            val tem = root!!.value
            root = null
            return tem
        }

        val extractedValue = swapBottommostRightmostNodeWithRoot()
        bubbleDownNewHead()
        return extractedValue
    }

    /**
     * Bubble Down the new root to maintain the heap property, so it will
     * swap the node with one of its children until it is in the correct spot,
     * to select the swapped children it selects the child with the min value
     */
    private fun bubbleDownNewHead(root: BinaryTreeNode<Int> = this.root!!) {
        var parent = root
        var leftChild = root.leftChild
        var rightChild = root.rightChild

        var parentIsGreater = getParentIsGreater(root = parent)

        while (parentIsGreater) {
            val lefChildIsSmaller = if (leftChild != null && rightChild != null) {
                leftChild.value < rightChild.value
            } else leftChild != null

            val childToBeSwapped = if (lefChildIsSmaller) {
                leftChild!!
            } else {
                rightChild!!
            }
            swapParentWithChildNodes(parent = parent, child = childToBeSwapped)

            leftChild = parent.leftChild
            rightChild = parent.rightChild
            parentIsGreater = getParentIsGreater(root = parent)
        }
    }

    private fun getParentIsGreater(root: BinaryTreeNode<Int>): Boolean {
        val parent = root
        val leftChild = root.leftChild
        val rightChild = root.rightChild

        return parent.value > (leftChild?.value ?: POSITIVE_INFINITY.toInt()) ||
                parent.value > (rightChild?.value ?: POSITIVE_INFINITY.toInt())
    }

    /**
     * Swap the BottommostRightmostNode at the root position and return the value of the
     * previous root (to execute the extract)
     */
    private fun swapBottommostRightmostNodeWithRoot(): Int {
        val temRoot = root!!
        val newRooT = findBottommostRightmostNode(root!!)
        if (newRooT.parent!!.leftChild == newRooT) {
            newRooT.parent!!.leftChild = null
        } else {
            newRooT.parent!!.rightChild = null
        }
        newRooT.parent = null

        newRooT.leftChild = temRoot.leftChild
        newRooT.rightChild = temRoot.rightChild
        temRoot.leftChild?.parent = newRooT
        temRoot.rightChild?.parent = newRooT

        root = newRooT

        return temRoot.value
    }

    private fun findBottommostRightmostNode(root: BinaryTreeNode<Int>): BinaryTreeNode<Int> {
        return findBottommostRightmostNode(
            visitedNode = root,
            desiredLevel = getDeeperLevelLeft(root)
        ).last()
    }

    /**
     * In an arraylist  add al the values that they meet the desired Level condition,
     * and as it traverse the heap in order then the las value of the array is the
     * BottommostRightmostNode so this can be obtained with the function of
     * arrayList.last()
     */
    private fun findBottommostRightmostNode(
        visitedNode: BinaryTreeNode<Int>,
        currentLevel: Int = 0,
        desiredLevel: Int,
        nodeList: ArrayList<BinaryTreeNode<Int>> = arrayListOf()
    ): ArrayList<BinaryTreeNode<Int>> {

        if (currentLevel == desiredLevel) {
            nodeList.add(visitedNode)
        }
        val newLevel = currentLevel + 1
        visitedNode.leftChild?.let {
            findBottommostRightmostNode(
                it,
                currentLevel = newLevel,
                desiredLevel = desiredLevel,
                nodeList = nodeList
            )
        }

        visitedNode.rightChild?.let {
            findBottommostRightmostNode(
                it,
                currentLevel = newLevel,
                desiredLevel = desiredLevel,
                nodeList = nodeList
            )
        }
        return nodeList
    }

    private fun bubbleUpNodeInserted(insertedNode: BinaryTreeNode<Int>) {
        var parent = insertedNode.parent
        while (parent != null && insertedNode.value < parent.value) {
            parent = swapParentWithChildNodes(parent = parent, child = insertedNode)
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
        val grandparent = parent.parent

        val childLeftChild = child.leftChild
        val childRightChild = child.rightChild

        if (grandparent?.rightChild == parent) {
            grandparent.rightChild = child
        } else {
            grandparent?.leftChild = child
        }
        child.parent = grandparent
        parent.parent = child

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
        if (grandparent == null) {
            root = child
        }
        return grandparent
    }

    private fun addNodeToTheBottom(root: BinaryTreeNode<Int>, insertedNode: BinaryTreeNode<Int>) {
        val levelToInsertTheNewNode = getDeeperLevelRight(root)
        val nodeWhereShouldBeAddedTheNewNode = findNodeToAddTheBottomNode(
            root,
            desiredLevel = levelToInsertTheNewNode
        )!!
        insertedNode.parent = nodeWhereShouldBeAddedTheNewNode
        if (nodeWhereShouldBeAddedTheNewNode.leftChild == null) {
            nodeWhereShouldBeAddedTheNewNode.leftChild = insertedNode
        } else {
            nodeWhereShouldBeAddedTheNewNode.rightChild = insertedNode
        }
    }

    private fun findNodeToAddTheBottomNode(
        visitedNode: BinaryTreeNode<Int>,
        currentLevel: Int = 0,
        desiredLevel: Int,
    ): BinaryTreeNode<Int>? {

        if (currentLevel == desiredLevel && visitedNode.hasAllChildren().not()) {
            return visitedNode
        }
        val newLevel = currentLevel + 1
        var newNodeFound = visitedNode.leftChild?.let {
            findNodeToAddTheBottomNode(
                it,
                currentLevel = newLevel,
                desiredLevel = desiredLevel
            )
        }
        if (newNodeFound != null) {
            return newNodeFound
        }
        newNodeFound = visitedNode.rightChild?.let {
            findNodeToAddTheBottomNode(
                it,
                currentLevel = newLevel,
                desiredLevel = desiredLevel
            )
        }
        return newNodeFound
    }

    fun printWithLevels(
        root: BinaryTreeNode<Int>? = this.root,
        currentLevel: Int = 0
    ) {
        if (root != null) {
            val newLevel = currentLevel + 1
            printWithLevels(
                root.leftChild,
                currentLevel = newLevel,
            )
            println("${root.value} level: $currentLevel")
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

    private fun getDeeperLevelLeft(root: BinaryTreeNode<Int>): Int {
        var deeperLevel = 0
        var currentNode = root
        while (currentNode.leftChild != null) {
            deeperLevel++
            currentNode = currentNode.leftChild!!
        }
        return deeperLevel
    }

}