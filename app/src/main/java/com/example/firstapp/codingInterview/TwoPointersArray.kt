package com.example.firstapp.codingInterview

fun main() {
    val arrayTestOne: IntArray = intArrayOf(-5, -2, 3, 4, 6)
    val targetOne = 7

    val arrayTestTwo: IntArray = intArrayOf(1, 1, 1)
    val targetTwo = 2

    var currentArrayTest = arrayTestOne
    var currentTarget = targetOne
    pairSumSorted(currentArrayTest, currentTarget).also { indexesList ->
        printResults(currentArrayTest, currentTarget, indexesList)
    }

    println()
    println()

    currentArrayTest = arrayTestTwo
    currentTarget = targetTwo

    pairSumSorted(currentArrayTest, currentTarget).also { indexesList ->
        printResults(currentArrayTest, currentTarget, indexesList)
    }

}

/*
* Inward traversal
* input nums =[-5,-2,3,4,6], target = 7
* output: [2,3]
* because num[2] + num[3] = 3+4=7
*
* input nums =[1,1,1], target = 2
* output: [0,1]
* because num[0] + num[1] = 1+1=2
* */
fun pairSumSorted(arrayTest: IntArray, target: Int): List<Int> {
    val indexArray: MutableList<Int> = mutableListOf()
    var leftIndex = 0
    var rightIndex = arrayTest.size - 1

    var leftValue = arrayTest[leftIndex]
    var rightValue = arrayTest[rightIndex]
    var sum = leftValue + rightValue


    while (leftIndex < rightIndex &&
        sum != target
    ) {
        if (sum < target) {
            leftIndex++
        } else {
            rightIndex--
        }
        leftValue = arrayTest[leftIndex]
        rightValue = arrayTest[rightIndex]
        sum = leftValue + rightValue
    }

    if (sum == target) {
        indexArray.add(leftIndex)
        indexArray.add(rightIndex)
    }

    return indexArray
}

private fun printResults(arrayTest: IntArray, target: Int, indexesList: List<Int>) {
    if (indexesList.isNotEmpty()) {
        val firstValueIndex = arrayTest[indexesList[0]]
        val secondValueIndex = arrayTest[indexesList[1]]
        val sum = firstValueIndex + secondValueIndex
        val textResult = if (sum == target) {

            "array: ${arrayTest.contentToString()}, target: $target \n" +
                    "indexes: ${indexesList.joinToString(", ")} \n" +
                    "$firstValueIndex + $secondValueIndex = $target"
        } else {
            "Error $firstValueIndex + $secondValueIndex is different $target"
        }

        println(textResult)
    } else {
        println(
            "The list of indexes is empty, then there were no pair of numbers in " +
                    "the array that their sum is the target num"
        )
    }
}

