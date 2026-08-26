package com.example.firstapp.codingpatterns.hashmapsandsets

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class PairSumUnsorted {

    @Test
    fun testPairSumUnsorted() {
        printHyphensSeparation()
        testAndPrintPairSumUnsorted(inputArray = arrayOf(-1, 3, 4, 2), target = 3)
        testAndPrintPairSumUnsorted(inputArray = arrayOf(-1, 2, 14, 2), target = 4)
        testAndPrintPairSumUnsorted(inputArray = arrayOf(-1, 3, 4, 2), target = 13)
    }

    private fun testAndPrintPairSumUnsorted(inputArray: Array<Int>, target: Int) {
//        val arrayOfIndexes = pairSumUnsorted(inputArray = inputArray, target = target)
        val arrayOfIndexes = pairSumUnsortedImproved(inputArray = inputArray, target = target)

        val message = if (arrayOfIndexes.isEmpty()) {
            "There aren't any combination that sum $target"
        } else {
            val firstItem = inputArray[arrayOfIndexes[0]]
            val secondItem = inputArray[arrayOfIndexes[1]]
            "the items: $firstItem + $secondItem = $target"
        }
        println(inputArray.contentToString())
        println(message)
        printHyphensSeparation()
    }

    private fun pairSumUnsortedImproved(inputArray: Array<Int>, target: Int): Array<Int> {
        // Hash map to store the integers as the key and the indexes as the values
        val valueIndexesHashMap = HashMap<Int, Int>()

        inputArray.forEachIndexed { index, value ->
            val requiredComplement = target - value
            val indexOfRequiredValue = valueIndexesHashMap[requiredComplement]
            /*
            * While we populate the hash map, we simultaneously check if the required complement
            * (the value needed to reach the target sum) already exists. This works because a
            * later value in the array can look up its required complement that was
            * previously stored.
            */
            if (indexOfRequiredValue != null) {
                return arrayOf(indexOfRequiredValue, index)
            } else {
                valueIndexesHashMap[value] = index
            }
        }
        // return an empty array if we traverse all the array, and we didn't find a pair of numbers
        return emptyArray()
    }

    private fun pairSumUnsorted(inputArray: Array<Int>, target: Int): Array<Int> {
        // Fill a  hash map with all the integers of the array as keys and their indexes as values
        val valueIndexesHashMap = fillHashMap(inputArray = inputArray)

        // Traverse all the array looking for a pir of numbers that sum the target value
        inputArray.forEachIndexed { index, value ->
            val valueRequired = target - value
            val indexesOfValueRequired = valueIndexesHashMap[valueRequired]

            if (indexesOfValueRequired != null) {
                when {
                    valueRequired == value -> {
                        if (indexesOfValueRequired.size >= 2) {
                            return indexesOfValueRequired.sliceArray(0..1)
                        }
                    }

                    else -> {
                        return arrayOf(index) + arrayOf(indexesOfValueRequired[0])
                    }
                }
            }
        }
        // return an empty array if we traverse all the array, and we didn't find a pair of numbers
        return emptyArray()
    }

    private fun fillHashMap(inputArray: Array<Int>): HashMap<Int, Array<Int>> {
        val valueIndexesHashMap = HashMap<Int, Array<Int>>()
        inputArray.forEachIndexed { index, value ->
            val currentIndexes = valueIndexesHashMap[value]
            if (currentIndexes == null) {
                valueIndexesHashMap[value] = arrayOf(index)
            } else {
                valueIndexesHashMap[value] = currentIndexes + arrayOf(index)
            }
        }
        return valueIndexesHashMap
    }
}
