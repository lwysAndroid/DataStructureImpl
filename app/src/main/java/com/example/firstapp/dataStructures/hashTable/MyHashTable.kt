package com.example.firstapp.dataStructures.hashTable

class MyHashTable {

    private val arraySize = 21
    private val arrayOfLinkedList = Array(size = arraySize) { MyLinkedList() }

    fun add(key: String, value: String) {
        val hash = hashGenerator(key = key)
        val index = getIndexFromHash(hashNumber = hash)
        val currentLinkedList = arrayOfLinkedList[index]
        val existingNode = currentLinkedList.find(nodeKey = key)
        if (existingNode == null) {
            currentLinkedList.add(Node(key = key, value = value))
        } else {
            existingNode.value = value
        }
    }

    fun getValue(key: String): String? {
        val node = findNode(key = key)
        return if (node == null) {
            null
        } else {
            node.value
        }
    }

    private fun findNode(key: String): Node? {
        val hash = hashGenerator(key = key)
        val index = getIndexFromHash(hashNumber = hash)
        val currentLinkedList = arrayOfLinkedList[index]
        return currentLinkedList.find(nodeKey = key)
    }

    private fun hashGenerator(key: String): Int {
        return key.length
    }

    private fun getIndexFromHash(hashNumber: Int): Int {
        return hashNumber % arraySize
    }
}