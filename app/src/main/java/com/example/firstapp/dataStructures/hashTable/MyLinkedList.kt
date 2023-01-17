package com.example.firstapp.dataStructures.hashTable

class MyLinkedList {
    var firstNode: Node? = null
    private var size: Int = 0

    fun getSize(): Int = size

    fun add(node: Node) {
        size++
        if (firstNode == null) {
            firstNode = node
            return
        }
//        getLastNode().nextNode = node
        getLastNodeRecursion(firstNode!!).nextNode = node
    }

    private fun getLastNode(): Node {
        var currentNode = firstNode!!
        while (currentNode.nextNode != null) {
            currentNode = currentNode.nextNode!!
        }
        return currentNode
    }

    private fun getLastNodeRecursion(node: Node): Node {
        if (node.nextNode == null) {
            return node
        }
        return getLastNodeRecursion(node.nextNode!!)
    }

    fun getAllNodes(): List<Node> {
        if (firstNode == null) {
            return emptyList()
        }
        val nodesList = ArrayList<Node>()
        return fillList(node = firstNode!!, nodesList = nodesList)
    }

    private fun fillList(node: Node, nodesList: List<Node>): List<Node> {
        (nodesList as ArrayList).add(node)
        if (node.nextNode == null) {
            return nodesList
        }
        return fillList(node = node.nextNode!!, nodesList = nodesList)
    }

    fun find(nodeKey: String): Node? {
        if (firstNode == null) {
            return null
        }
        return findRecursive(node = firstNode!!, nodeKey = nodeKey)
    }

    private fun findRecursive(node: Node, nodeKey: String): Node? {
        if (node.key == nodeKey) {
            return node
        }

        return if (node.nextNode == null) {
            null
        } else {
            findRecursive(node = node.nextNode!!, nodeKey = nodeKey)
        }
    }

}