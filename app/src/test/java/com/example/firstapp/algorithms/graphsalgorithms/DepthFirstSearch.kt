package com.example.firstapp.algorithms.graphsalgorithms

import com.example.firstapp.algorithms.graphsalgorithms.BreadthFirstSearch.Companion.getGraph
import com.example.firstapp.algorithms.graphsalgorithms.BreadthFirstSearch.Companion.you
import com.example.firstapp.algorithms.printHyphensSeparation
import org.junit.Test

class DepthFirstSearch {

    @Test
    fun testBDepthFirstSearch() {
        printHyphensSeparation()
        val graph = getGraph()
        val mangoSeller = depthFirstSearch(graph = graph, centerPerson = you)

        // Result presentation
        val message = if (mangoSeller != null) {
            "The mango seller in your network is: ${mangoSeller.name}."
        } else {
            "No one in your network is a mango seller."
        }
        println(message)
        printHyphensSeparation()
    }

    private fun depthFirstSearch(
        graph: HashMap<Person, Array<Person>>,
        centerPerson: Person,
        verifiedPersons: MutableSet<Person> = mutableSetOf(),
    ): Person? {

        val closeNetworkOfCurrentPerson: Array<Person>? = graph[centerPerson]
        var innerMangoSeller: Person? = null

        closeNetworkOfCurrentPerson?.forEach { currentPerson ->
            val message =
                "${currentPerson.name} has been validated ${verifiedPersons.contains(currentPerson)}"
            println(message)

            if (currentPerson.isMangoSeller) { // Base case or Termination Condition
                return currentPerson
            } else {
                if (innerMangoSeller == null) {
                    innerMangoSeller = if (verifiedPersons.contains(currentPerson)) {
                        null
                    } else { // Recursive case
                        verifiedPersons.add(currentPerson)
                        depthFirstSearch(
                            graph = graph,
                            centerPerson = currentPerson,
                            verifiedPersons = verifiedPersons,
                        )
                    }
                }
            }
        }
        return innerMangoSeller
    }
}