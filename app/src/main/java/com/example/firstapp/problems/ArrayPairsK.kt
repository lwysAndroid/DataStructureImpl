package com.example.firstapp.problems

/**
 * Given an array of distinct integer values, count the number of pair of integers that
 * have difference k. For example, given the array {1,7,5,9,2,12,3} and the difference
 * k = 2, there are four (4) pairs wth difference 2: (1 3) (3 5) (5 7) (7 9)
 */

fun countPair(arr: Array<Int>, gap: Int): Int {
    val hashArray = HashMap<Int, Boolean>()
    val hashMinPair = HashMap<Int, Boolean>()
    var counter = 0

    arr.forEach { currentInt ->
        if (hashArray[currentInt] == null) {
            hashArray.set(key = currentInt, value = true)
        }
        val min = currentInt - gap
        val max = currentInt + gap
        if (hashArray[min] != null) {
            val currentCounter = validateMin(hashMin = hashMinPair, minValue = min)
            counter += currentCounter
            if (currentCounter != 0) {
                println("($min,$currentInt)")
            }
        }
        if (hashArray[max] != null) {
            val currentCounter = validateMin(hashMin = hashMinPair, minValue = currentInt)
            counter += currentCounter
            if (currentCounter != 0) {
                println("($currentInt,$max)")
            }
        }
    }
    return counter
}

private fun validateMin(hashMin: HashMap<Int, Boolean>, minValue: Int): Int {
    if (hashMin[minValue] == null) {
        hashMin.set(key = minValue, value = true)
        return 1
    }
    return 0
}

fun countPairTest() {
    val arrTest = arrayOf(1, 7, 5, 9, 2, 12, 3)
    val gap = 2
    countPair(arr = arrTest, gap = gap).also { println(it) }
}