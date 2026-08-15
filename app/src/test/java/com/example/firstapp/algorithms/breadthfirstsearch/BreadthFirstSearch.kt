package com.example.firstapp.algorithms.breadthfirstsearch

import com.example.firstapp.algorithms.printHyphensSeparation
import org.junit.Test
import java.util.ArrayDeque
import java.util.Queue

class BreadthFirstSearch {

    private val you = Person("you")

    @Test
    fun testBreadthFirstSearch() {
        printHyphensSeparation()

        val graph = getGraph()
        val mangoSeller = breadthFirstSearch(graph = graph, startPerson = you)

        // Result presentation
        val message = if (mangoSeller != null) {
            "The mango seller in your network is: ${mangoSeller.name}."
        } else {
            "No one in your network is a mango seller."
        }
        println(message)

        printHyphensSeparation()
    }


    /**
     * Searches the user's social network using a Breadth-First Search (BFS) algorithm
     * to find the closest person where [Person.isMangoSeller] is true.
     *
     * @param startPerson The root node/person from which to begin the search.
     * @return The first [Person] found who sells mangoes, or `null` if none exists in the network.
     */
    private fun breadthFirstSearch(
        graph: HashMap<Person, Array<Person>>,
        startPerson: Person
    ): Person? {
        val searchQueue: Queue<Person> = ArrayDeque()
        val closeNetworkOfStartPerson = graph[startPerson]
        // Adds all of your out-neighbors to the search queue
        closeNetworkOfStartPerson?.forEach { searchQueue.add(it) }
        // This set is how you keep track of which people you’ve searched before.
        val verifiedPersons: MutableSet<Person> = mutableSetOf()

        while (searchQueue.isNotEmpty()) {
            val currentPerson = searchQueue.remove()

            // Only search this person if you haven’t already searched them.
            if (!verifiedPersons.contains(currentPerson)) {
                if (currentPerson.isMangoSeller) {
                    return currentPerson
                } else {
                    // Marks this person as searched
                    verifiedPersons.add(currentPerson)
                    val closeNetworkCurrentPerson = graph[currentPerson]
                    // Add all of this person’s friends to the search queue.
                    closeNetworkCurrentPerson?.forEach { searchQueue.add(it) }
                }
            }
        }
        // If you reached here, no one in the queue is a mango seller.
        return null
    }


    /**
     * Models a directed graph using a HashMap, where each key represents a node and its
     * value is an array of its immediate outbound neighbors (friends).
     */
    private fun getGraph(): HashMap<Person, Array<Person>> {
        val graph = HashMap<Person, Array<Person>>()

        val alice = Person("alice")
        val bob = Person("bob")
        val claire = Person("claire")
        val anuj = Person("anuj", isMangoSeller = true)
        val peggy = Person("peggy")
        val thom = Person("thom")
        val jonny = Person("jonny")

        graph[you] = arrayOf(alice, bob, claire)
        graph[bob] = arrayOf(anuj, peggy)
        graph[alice] = arrayOf(peggy)
        graph[claire] = arrayOf(thom, jonny)
        graph[anuj] = arrayOf()
        graph[peggy] = arrayOf()
        graph[thom] = arrayOf()
        graph[jonny] = arrayOf()

        return graph
    }
}
