package com.example.firstapp.dataStructures.hashTable

class Node(val key: String, var value: String) {
    var nextNode: Node? = null

    override fun toString(): String {
        val nextNodeMessage = if (nextNode == null) {
            "null"
        } else {
            nextNode!!.key
        }
        return "key:$key value:$value nextNodeKey:$nextNodeMessage"
    }

}