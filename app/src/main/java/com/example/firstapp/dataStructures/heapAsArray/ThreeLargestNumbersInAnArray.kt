package com.example.firstapp.dataStructures.heapAsArray


fun main() {
//    val testArray = arrayListOf(10, 1, 9, 2, 8, 3, 7, 4, 6, 5)
    val testArray = arrayListOf(10, 1, 9, 20, 8, 3, 70, 4, 60, 5)
    getThreeLargestNumbersOfTheArray(array = testArray).forEach { println(it) }
}

/**
 * Time complexity of the algorithm is O(N log(K))
 * Where N is the size of the array and K is the number of hte largest number, in this case
 * K = 3
 *
 * The space complexity is K, which is the number of hte largest number
 */
fun getThreeLargestNumbersOfTheArray(array: ArrayList<Int>): ArrayList<Int> {
    val threeLargestNumbers = arrayListOf<Int>()
    val minHeap = MinHeapAsArray()
    (0..2).forEach { minHeap.insert(array[it]) }

    (3..array.lastIndex).forEach {
        minHeap.insert(array[it])
        minHeap.delete()
    }

    repeat((0..2).count()) {
        minHeap.delete()?.let { it1 -> threeLargestNumbers.add(it1) }
    }

    return threeLargestNumbers
}