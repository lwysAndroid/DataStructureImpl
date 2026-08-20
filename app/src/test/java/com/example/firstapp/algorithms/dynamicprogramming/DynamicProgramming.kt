package com.example.firstapp.algorithms.dynamicprogramming

import com.example.firstapp.algorithms.printHyphensSeparation
import org.junit.Test

class DynamicProgramming {

    private val optimalSetOfItemsDynamicProgramming = OptimalSetOfItemsDynamicProgramming()

    @Test
    fun testDynamicProgramming() {
        printHyphensSeparation()

        testDynamicProgramming(
            setOfAvailableItems = getCampingItems(),
            availableWeight = campingAvailableWeight,
            name = "Camping"
        )

        printHyphensSeparation()

        testDynamicProgramming(
            setOfAvailableItems = getStoreItems(),
            availableWeight = storeAvailableWeight,
            name = "Sore"
        )

        printHyphensSeparation()

        testDynamicProgramming(
            setOfAvailableItems = getTravelItems(),
            availableWeight = travelAvailableWeight,
            name = "Travel"
        )

        printHyphensSeparation()
    }

    private fun testDynamicProgramming(
        setOfAvailableItems: Set<Item>,
        availableWeight: Float,
        name: String,
    ) {
        val optimalSetOfItems =
            optimalSetOfItemsDynamicProgramming.getOptimalSetOfItemsDynamicProgramming(
                setOfAvailableItems = setOfAvailableItems,
                availableWeight = availableWeight
            )
        printResults(
            optimalSetOfItems = optimalSetOfItems,
            availableKnapsackWeight = availableWeight,
            setOfAvailableItems = setOfAvailableItems,
            name = name
        )

    }

    private fun printResults(
        optimalSetOfItems: Set<Item>,
        availableKnapsackWeight: Float,
        setOfAvailableItems: Set<Item>,
        name: String,
    ) {
        var maxWorthValue = 0F
        setOfAvailableItems.forEach { maxWorthValue += it.worthValue }

        var totalWight = 0F
        var totalWorthValue = 0F
        optimalSetOfItems.forEach {
            totalWight += it.weight
            totalWorthValue += it.worthValue
        }
        println("Optimal Set Of Items for $name")
        println("Item | Weight | WorthValue")
        optimalSetOfItems.forEach {
            println("${it.name} | ${it.weight} | ${it.worthValue}")
        }
        println("TotalWight: $totalWight | TotalWorthValue: $totalWorthValue")
        println("AvailableWeight: $availableKnapsackWeight | MaxWorthValue: $maxWorthValue")
    }
}