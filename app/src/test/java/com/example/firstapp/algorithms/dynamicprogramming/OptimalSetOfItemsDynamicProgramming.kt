package com.example.firstapp.algorithms.dynamicprogramming

import java.util.TreeSet

class OptimalSetOfItemsDynamicProgramming {
    fun getOptimalSetOfItemsDynamicProgramming(
        setOfAvailableItems: Set<Item>,
        availableWeight: Float,
    ): Set<Item> {
        // Available weight options
        val weightOptions: TreeSet<Float> =
            getWeightOptions(items = setOfAvailableItems, maxWeight = availableWeight)

        // Row with the max current values
        var maxWorthValueAndItemsPerWeight = HashMap<Float, WorthValueAndItems?>()

        // Create a max values row for each item
        setOfAvailableItems.forEach { item ->
            // Row with the max values for this item
            val maxWorthValueAndItemsPerWeightCurrentItem = HashMap<Float, WorthValueAndItems?>()

            // Iterate every item over all weight available
            weightOptions.forEach { currentWeight ->
                val itemWeight = item.weight
                val itemWorthValue = item.worthValue
                // Operation with the item when it fits in the current weight
                if (currentWeight >= itemWeight) {
                    // Ge teh max value for the remainin space
                    val remainingAvailableWeight = currentWeight - itemWeight
                    val maxWorthValueAndItemsOfRemainingAvailableWeight =
                        maxWorthValueAndItemsPerWeight[remainingAvailableWeight]

                    // Calculate the possible max value
                    val possibleMaxWorthValue =
                        itemWorthValue + (maxWorthValueAndItemsOfRemainingAvailableWeight?.worthValue
                            ?: 0F)
                    val maxWorthValue =
                        maxWorthValueAndItemsPerWeight[currentWeight]?.worthValue ?: 0F

                    // If the possible max value is grater then the current one, use it
                    if (possibleMaxWorthValue > maxWorthValue) {
                        val worthValueAndItems = WorthValueAndItems(
                            worthValue = possibleMaxWorthValue,
                            items = (maxWorthValueAndItemsOfRemainingAvailableWeight?.items
                                ?: emptySet()) + setOf(item)
                        )
                        maxWorthValueAndItemsPerWeightCurrentItem[currentWeight] =
                            worthValueAndItems
                    } else {
                        // If the previous value is grater, use it instead
                        maxWorthValueAndItemsPerWeightCurrentItem[currentWeight] =
                            maxWorthValueAndItemsPerWeight[currentWeight]
                    }
                } else {
                    // If the current item doesn't fit in the current weight, use the previous max value
                    maxWorthValueAndItemsPerWeightCurrentItem[currentWeight] =
                        maxWorthValueAndItemsPerWeight[currentWeight]
                }
            }

            // Update the hash table with the maximum values and items using the newly obtained
            // this new one will have the maximum values updated with the values from the last item.
            maxWorthValueAndItemsPerWeight = maxWorthValueAndItemsPerWeightCurrentItem
        }

        // Get the items for the maximum available weight; this also corresponds to the maximum value.
        val optimalSet =
            maxWorthValueAndItemsPerWeight[weightOptions.last()]?.items ?: emptySet()

        return optimalSet
    }

    private fun getWeightOptions(items: Set<Item>, maxWeight: Float): TreeSet<Float> {
        val minWeightAvailable = getMinWeightOfItems(items = items)
        val weightOptions = TreeSet<Float>()
        var currentWeight = minWeightAvailable
        while (currentWeight <= maxWeight) {
            weightOptions.add(currentWeight)
            currentWeight += minWeightAvailable
        }
        return weightOptions
    }

    private fun getMinWeightOfItems(items: Set<Item>): Float {
        var minWeight = Float.MAX_VALUE
        items.forEach { item ->
            if (item.weight < minWeight) {
                minWeight = item.weight
            }
        }
        return minWeight
    }
}