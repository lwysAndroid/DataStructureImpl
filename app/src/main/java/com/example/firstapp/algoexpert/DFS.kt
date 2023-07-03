package com.example.firstapp.algoexpert

import java.util.ArrayDeque


class NodeDFS(name: String) {
    val name: String = name
    val children = mutableListOf<NodeDFS>()
    val listOfNames = mutableListOf<String>()


    fun depthFirstSearch(): List<String> {
        var currentNode: NodeDFS? = this
        val listOfNames = mutableListOf<String>()
        val stackOfNodes = ArrayDeque<NodeDFS>()
        val visitedNodes = HashMap<NodeDFS, Boolean>()


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

    fun depthFirstSearch(node: NodeDFS = this): List<String> {
        val currentName = node.name
        listOfNames.add(currentName)
        node.children.forEach(){
            depthFirstSearch(it)
        }
        return listOfNames
    }

}
