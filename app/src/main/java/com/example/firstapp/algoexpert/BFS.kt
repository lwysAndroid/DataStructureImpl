package com.example.firstapp.algoexpert

import java.util.*

class NodeBFS(name: String) {

    val name: String = name
    val children = mutableListOf<NodeBFS>()

    fun breadthFirstSearch(): List<String> {
        val queueOfNodes: Queue<NodeBFS> = LinkedList()
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