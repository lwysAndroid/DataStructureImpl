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


    /**
     * Reverse the linked list starting from the node that has the value given,
     * if the value isn't in the linked list this method doesn't apply any change
     * @param nodeKey is the key of the node where the reverse should start
     * @return true if the node with the key was found and the reverse was applied or
     * false it wasn't found and no change was applied
     */
    fun reverseFromNode(nodeKey: String): Boolean {
        if (head == null) {
            return false
        }

        var previousNode: Node? = head!!.next
        var currentNode = head!!
        if (currentNode.value == nodeKey) {
            reverse(head = currentNode)
            return true
        }

        var valueFound = false
        while (currentNode.next != null) {
            previousNode = currentNode
            currentNode = currentNode.next!!
            if (currentNode.value == nodeKey) {
                valueFound = true
                break
            }
        }
        if (valueFound) {
            val newInnerHead = reverse(head = currentNode)
            previousNode!!.next = newInnerHead
            return true
        }
        return false

    }


    fun reverse() {
        head = head?.let { reverse(it) }
    }

    /**
     * Reverse the linked list using the head as the input value, it return the new head which
     * will be the previous tail
     * @param head the head of the linked List
     * @return the new head after of reversing the linked List
     */
    private fun reverse(head: Node): Node {
        val currentNext = head.next
        if (currentNext == null) {
            return head
        }
        head.next = null
        val newHead = recursiveReverse(previousNode = head, currentNode = currentNext)
        return newHead
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