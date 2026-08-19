package com.example.firstapp.algorithms.graphsalgorithms.dijkstraalgorithm

import com.example.firstapp.algorithms.printHyphensSeparation
import org.junit.Test

/**
 * Dijkstra’s algorithm is used to calculate the shortest path for a weighted graph.
 *
 * Dijkstra’s algorithm only works on graphs with no cycles,
 * where all the edges are nonnegative.
 */
class DijkstraAlgorithm {

    // Example nodes
    private val startNode = "START"
    private val aNode = "A"
    private val bNode = "B"
    private val finishNode = "FINISH"

    // Exercise nodes
    private val startNodeExercise = "START_EX"
    private val aNodeExercise = "A_EX"
    private val bNodeExercise = "B_EX"
    private val cNodeExercise = "C_EX"
    private val dNodeExercise = "D_EX"
    private val finishNodeExercise = "FINISH_EX"


    @Test
    fun testDijkstraAlgorithm() {
        printHyphensSeparation()

        // Example
        val (cost, path) = dijkstraAlgorithm(
            initNode = startNode,
            goalNode = finishNode,
            graph = createGraph()
        )
        printResults(cost = cost, path = path)

        printHyphensSeparation()

        // Exercise
        val (costExercise, pathExercise) = dijkstraAlgorithm(
            initNode = startNodeExercise,
            goalNode = finishNodeExercise,
            graph = createExerciseGraph()
        )
        printResults(cost = costExercise, path = pathExercise)

        printHyphensSeparation()

    }

    private fun printResults(cost: Int?, path: ArrayList<String?>) {
        println("Cost: $cost")
        println("Path: $path")
    }

    private fun createExerciseGraph(): HashMap<String, HashMap<String, Int>> {
        val graph = HashMap<String, HashMap<String, Int>>()
        graph[startNodeExercise] = hashMapOf(aNodeExercise to 5, bNodeExercise to 2)
        graph[aNodeExercise] = hashMapOf(cNodeExercise to 4, dNodeExercise to 2)
        graph[bNodeExercise] = hashMapOf(aNodeExercise to 8, dNodeExercise to 7)
        graph[cNodeExercise] = hashMapOf(finishNodeExercise to 3, dNodeExercise to 6)
        graph[dNodeExercise] = hashMapOf(finishNodeExercise to 1)
        graph[finishNodeExercise] = hashMapOf()
        return graph
    }

    private fun createGraph(): HashMap<String, HashMap<String, Int>> {
        val graph = HashMap<String, HashMap<String, Int>>()
        graph[startNode] = hashMapOf(aNode to 6, bNode to 2)
        graph[aNode] = hashMapOf(finishNode to 1)
        graph[bNode] = hashMapOf(aNode to 3, finishNode to 5)
        graph[finishNode] = hashMapOf()
        return graph
    }

    private fun dijkstraAlgorithm(
        initNode: String,
        goalNode: String,
        graph: HashMap<String, HashMap<String, Int>>
    ): Pair<Int, ArrayList<String?>> {
        val hashMapWithParentsAndCosts: HashMap<String, ParentAndCost> =
            getHashMapWithParentsAndCosts(initNode = initNode, graph = graph)
        val processedNodes: MutableSet<String> = mutableSetOf()
        var node = findLowestCostNode(
            currentNode = initNode,
            hashMapWithParentsAndCosts = hashMapWithParentsAndCosts,
            processedNodes = processedNodes,
            graph = graph
        )

        while (node != null) {
            val nodeCost = hashMapWithParentsAndCosts[node]?.cost ?: 0
            val nodeNeighbors = graph[node]
            nodeNeighbors?.keys?.forEach { neighborNode ->
                // Sum the cost of the current node plus the wight of the current node to its neighbor
                val newNeighborNodeCost = nodeCost + (nodeNeighbors[neighborNode] ?: 0)
                // Get the cost of the neighbor from the hash map
                val currentNeighborCost = hashMapWithParentsAndCosts[neighborNode]?.cost ?: 0
                // Update the hash map of costs and parents if the new cost is lower
                if (currentNeighborCost > newNeighborNodeCost) {
                    val newParentAndCost = ParentAndCost(parent = node, cost = newNeighborNodeCost)
                    hashMapWithParentsAndCosts[neighborNode] = newParentAndCost
                }
            }
            processedNodes.add(node)
            node = findLowestCostNode(
                currentNode = node,
                hashMapWithParentsAndCosts = hashMapWithParentsAndCosts,
                processedNodes = processedNodes,
                graph = graph
            )
        }
        val path = arrayListOf<String?>()
        var currentNodePath: String? = goalNode
        while (currentNodePath != null) {
            path.add(currentNodePath)
            currentNodePath = hashMapWithParentsAndCosts[currentNodePath]?.parent
        }
        path.reverse()
        val goalNodeCost = hashMapWithParentsAndCosts[goalNode]?.cost ?: 0
        return Pair(first = goalNodeCost, second = path)
    }

    private fun getHashMapWithParentsAndCosts(
        initNode: String,
        graph: HashMap<String, HashMap<String, Int>>
    ): HashMap<String, ParentAndCost> {
        val neighborsOfInitNode = graph[initNode]
        val allNodes = graph.keys
        val hashMapWithParentsAndCosts = HashMap<String, ParentAndCost>()

        // Add all nodes with default values, parent = null, cost = Int.MAX_VALUE
        allNodes.forEach {
            if (it != initNode) {
                hashMapWithParentsAndCosts[it] = ParentAndCost(parent = null, cost = Int.MAX_VALUE)
            }
        }
        // Update values of the neighbors of the initial node with the real values
        neighborsOfInitNode?.forEach { (nodeName, cost) ->
            hashMapWithParentsAndCosts[nodeName] = ParentAndCost(parent = initNode, cost = cost)
        }
        return hashMapWithParentsAndCosts
    }

    private fun findLowestCostNode(
        currentNode: String,
        hashMapWithParentsAndCosts: HashMap<String, ParentAndCost>,
        processedNodes: Set<String>,
        graph: HashMap<String, HashMap<String, Int>>
    ): String? {
        var lowestCost = Int.MAX_VALUE
        var lowestCostNode: String? = null
        val neighborsNodes = graph[currentNode]?.keys

        neighborsNodes?.forEach { node ->
            if (!processedNodes.contains(node)) {
                val currentCost = hashMapWithParentsAndCosts[node]?.cost ?: Int.MAX_VALUE
                if (currentCost < lowestCost) {
                    lowestCost = currentCost
                    lowestCostNode = node
                }
            }
        }
        return lowestCostNode
    }

}
