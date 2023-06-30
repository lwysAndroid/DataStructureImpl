package com.example.firstapp.algoexpert

import java.util.ArrayDeque


class Node(name: String) {
    val name: String = name
    val children = mutableListOf<Node>()
    val listOfNames = mutableListOf<String>()


    fun depthFirstSearch(): List<String> {
        var currentNode: Node? = this
        val listOfNames = mutableListOf<String>()
        val stackOfNodes = ArrayDeque<Node>()
        val visitedNodes = HashMap<Node, Boolean>()


        while (currentNode != null) {
            var existNewNode = false
            if (!visitedNodes.contains(currentNode)) {
                val currentName = currentNode.name
                visitedNodes[currentNode] = true
                listOfNames.add(currentName)
            }

            if (currentNode.children.isNotEmpty()) {
                currentNode.children.forEach() { childNode ->
                    if (!existNewNode&&!visitedNodes.contains(childNode)) {
                        stackOfNodes.push(currentNode)
                        currentNode = childNode
                        existNewNode = true
                    }
                }

            } else {
                currentNode = if (stackOfNodes.isNotEmpty()) {
                    existNewNode = true
                    stackOfNodes.pop()
                } else {
                    null
                }
            }

            if (!existNewNode) {
                currentNode = if (stackOfNodes.isNotEmpty()) {
                    stackOfNodes.pop()
                } else {
                    null
                }
            }

        }

        return listOfNames
    }

    fun depthFirstSearch(node: Node = this): List<String> {
        val currentName = node.name
        listOfNames.add(currentName)
        node.children.forEach(){
            depthFirstSearch(it)
        }
        return listOfNames
    }

}
