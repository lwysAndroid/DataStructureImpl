package com.example.firstapp.algoexpert

class Node(name: String) {
    val name: String = name
    val children = mutableListOf<Node>()
    var depthFirstSearchList = ArrayList<String>()

    fun depthFirstSearch(): List<String> {
        // Write your code here.
        return listOf()
    }

    fun depthFirstSearch(node: Node = this): List<String> {
        depthFirstSearchList.add(node.name)
        node.children.forEach { childNode ->
            depthFirstSearch(childNode)
        }
        return depthFirstSearchList
    }


}