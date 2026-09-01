package com.example.firstapp.codingpatterns.graphs

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class GraphDeepCopy {

    @Test
    fun testGraphDeepCopy() {
        printHyphensSeparation()
        val node = createGraph()
        val copiedGraph = graphDeepCopyDFS(node = node)

        val graphsAreEquals = node === copiedGraph
        println("graphsAreEquals: $graphsAreEquals")
        printHyphensSeparation()
        traverseGraphDFS(node = node)
        printHyphensSeparation()
        traverseGraphBFS(node = copiedGraph)
        val listOfInitialNode = getSetOFGraphBFS(node = node)
        val listOfCopiedNode = getSetOFGraphBFS(node = copiedGraph)
        printHyphensSeparation()
        listOfInitialNode.forEach { originalNode ->
            val copiedNode = listOfCopiedNode.find { originalNode.value == it.value }
            val isTheSameElement = copiedNode === originalNode
            println("$originalNode isTheSameElement: $isTheSameElement")
        }
        printHyphensSeparation()
        listOfInitialNode.forEach { originalNode ->
            val copiedNode = listOfInitialNode.find { originalNode.value == it.value }
            val isTheSameElement = copiedNode === originalNode
            println("$originalNode isTheSameElement: $isTheSameElement")
        }
    }

    private fun getSetOFGraphBFS(node: GraphNode): MutableList<GraphNode> {
        val queue = ArrayDeque<GraphNode>()
        queue.add(node)
        val mutableSet = mutableListOf<GraphNode>()
        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            if (!mutableSet.contains(currentNode)) {
                mutableSet.add(currentNode)
                currentNode.array?.forEach {
                    if (!mutableSet.contains(it)) {
                        queue.add(it)
                    }
                }
            }
        }
        return mutableSet
    }

    private fun graphDeepCopyDFS(
        node: GraphNode,
        copiedNodes: HashMap<GraphNode, GraphNode> = HashMap()
    ): GraphNode {

        val copiedNode = copiedNodes[node]

        if (copiedNode != null) {
            return copiedNode
        } else {
            val copiedNode = GraphNode(value = node.value)
            copiedNodes[node] = copiedNode
            val neighborNodesList = ArrayList<GraphNode>()
            node.array?.forEach {
                val neighborNode = graphDeepCopyDFS(node = it, copiedNodes = copiedNodes)
                neighborNodesList.add(neighborNode)
            }
            copiedNode.array = neighborNodesList.toTypedArray()
            return copiedNode
        }
    }

    private fun traverseGraphDFS(
        node: GraphNode,
        visitedNodes: MutableSet<GraphNode> = mutableSetOf()
    ) {
        println("${node.value}")
        visitedNodes.add(node)
        node.array?.forEach {
            if (!visitedNodes.contains(it)) {
                traverseGraphDFS(node = it, visitedNodes)
            }
        }
    }

    private fun traverseGraphBFS(node: GraphNode) {
        val visitedNodes: MutableSet<GraphNode> = mutableSetOf()
        val addedToQueueNodes: MutableSet<GraphNode> = mutableSetOf()
        val queue = ArrayDeque<GraphNode>()
        queue.add(node)
        addedToQueueNodes.add(node)
        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            if (!visitedNodes.contains(currentNode)) {
                visitedNodes.add(currentNode)
                println("${currentNode.value}")
                currentNode.array?.forEach {
                    if (!addedToQueueNodes.contains(it)) {
                        addedToQueueNodes.add(it)
                        queue.add(it)
                    }
                }
            }
        }
    }

    private fun createGraph(): GraphNode {
        val graphNode_0 = GraphNode(value = 0)
        val graphNode_1 = GraphNode(value = 1)
        val graphNode_2 = GraphNode(value = 2)
        val graphNode_3 = GraphNode(value = 3)

        graphNode_0.array = arrayOf(graphNode_1, graphNode_2)
        graphNode_1.array = arrayOf(graphNode_0, graphNode_2)
        graphNode_2.array = arrayOf(graphNode_0, graphNode_1, graphNode_3)
        graphNode_3.array = arrayOf(
            graphNode_2,
            GraphNode(value = 4)
        )
        return graphNode_0
    }
}
