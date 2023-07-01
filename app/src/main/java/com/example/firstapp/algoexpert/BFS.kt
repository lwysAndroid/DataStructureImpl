package com.example.firstapp.algoexpert

import java.util.*

class Node(name: String) {

    val name: String = name
    val children = mutableListOf<Node>()

    fun breadthFirstSearch(): List<String> {
        val queueOfNodes: Queue<Node> = LinkedList()
        queueOfNodes.add(this)
        val listOfNames = mutableListOf<String>()

        while (queueOfNodes.isNotEmpty()){
            val currentNode = queueOfNodes.poll()!!
            listOfNames.add(currentNode.name)
            currentNode.children.forEach {
                queueOfNodes.add(it)
            }
        }

        return listOfNames
    }
}