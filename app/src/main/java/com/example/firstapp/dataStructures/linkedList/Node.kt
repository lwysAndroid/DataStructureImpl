package com.example.firstapp.dataStructures.linkedList

class Node(val key: String, var value: String) {
    var next: Node? = null

    override fun toString(): String {
        val nextNodeMessage = if (next == null) {
            "null"
        } else {
            next!!.key
        }
        return "key:$key value:$value nextNodeKey:$nextNodeMessage"
    }

}