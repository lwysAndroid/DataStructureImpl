package com.example.firstapp.codingpatterns.trees

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class InvertBinaryTree {

    @Test
    fun testInvertBinaryTree() {
        printHyphensSeparation()
        val head = createBinaryTree()
        head.printTree()

        /*val binaryTreeInvertedDFS = invertBinaryTreeRecursivelyDFS(root = head)
        printHyphensSeparation()
        binaryTreeInvertedDFS.printTree()*/

        val binaryTreeInvertedBFS = invertBinaryTreeBFS(root = head)
        printHyphensSeparation()
        binaryTreeInvertedBFS.printTree()
    }

    // This function traverse al the nodes using Depth First Search
    private fun invertBinaryTreeRecursivelyDFS(root: BinaryTreeNode<Int>?): BinaryTreeNode<Int>? {
        // Base case or End Condition
        if (root == null) {
            return null
        }

        val left = root.left
        val right = root.right
        // flip nodes
        root.left = right
        root.right = left

        // Recursive condition to flip all nodes
        invertBinaryTreeRecursivelyDFS(root = left)
        invertBinaryTreeRecursivelyDFS(root = right)
        return root
    }

    // This function traverse al the nodes using Breadth First Search
    private fun invertBinaryTreeBFS(root: BinaryTreeNode<Int>): BinaryTreeNode<Int> {
        val queue = ArrayDeque<BinaryTreeNode<Int>>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            val left = currentNode.left
            val right = currentNode.right
            // flip nodes
            currentNode.left = right
            currentNode.right = left
            // Add children nodes to the queue
            left?.let { queue.add(it) }
            right?.let { queue.add(it) }
        }
        return root
    }


    private fun createBinaryTree(): BinaryTreeNode<Int> {
        val head = BinaryTreeNode(value = 5)

        BinaryTreeNode(
            value = 1,
            left = BinaryTreeNode(value = 7),
            right = BinaryTreeNode(value = 6)
        ).also {
            head.left = it
        }

        BinaryTreeNode(
            value = 8,
            right = BinaryTreeNode(value = 4)
        ).also {
            head.right = it
        }
        return head
    }
}
