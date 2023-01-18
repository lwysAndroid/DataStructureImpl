package com.example.firstapp.dataStructures.linkedList

class MyLinkedList {
    private var head: Node? = null
    private var size: Int = 0

    fun getSize(): Int = size

    fun add(node: Node) {
        size++
        if (head == null) {
            head = node
            return
        }
//        getLastNode().nextNode = node
        getLastNodeRecursion(head!!).next = node
    }

    private fun getLastNode(): Node {
        var currentNode = head!!
        while (currentNode.next != null) {
            currentNode = currentNode.next!!
        }
        return currentNode
    }

    private fun getLastNodeRecursion(node: Node): Node {
        if (node.next == null) {
            return node
        }
        return getLastNodeRecursion(node.next!!)
    }

    fun getAllNodes(): List<Node> {
        if (head == null) {
            return emptyList()
        }
        val nodesList = ArrayList<Node>()
        return fillList(node = head!!, nodesList = nodesList)
    }

    private fun fillList(node: Node, nodesList: List<Node>): List<Node> {
        (nodesList as ArrayList).add(node)
        if (node.next == null) {
            return nodesList
        }
        return fillList(node = node.next!!, nodesList = nodesList)
    }

    fun find(nodeKey: String): Node? {
        if (head == null) {
            return null
        }
        return findRecursive(node = head!!, nodeKey = nodeKey)
    }

    private fun findRecursive(node: Node, nodeKey: String): Node? {
        if (node.key == nodeKey) {
            return node
        }

        return if (node.next == null) {
            null
        } else {
            findRecursive(node = node.next!!, nodeKey = nodeKey)
        }
    }

    fun reverse() {
        head?.let { _head ->
            val currentNext = _head.next
            if (currentNext == null) {
                return
            }
            _head.next = null
            val newHead = recursiveReverse(previousNode = _head, currentNode = currentNext)
            head = newHead
        }
    }

    private fun recursiveReverse(previousNode: Node, currentNode: Node): Node {
        val currentNext = currentNode.next
        currentNode.next = previousNode
        if (currentNext == null) {
            return currentNode
        }
        return recursiveReverse(previousNode = currentNode, currentNode = currentNext)
    }

}