package com.example.firstapp.dataStructures.dynamicArray


class DynamicArray(private var maxSize: Int = 10) {

    private var arrayContainer: Array<String?> = arrayOfNulls(size = maxSize)
    private var indexOfEmptySpace = 0

    fun add(value: String) {
        if (maxSize == indexOfEmptySpace) {
            resizeArray()
        }
        arrayContainer[indexOfEmptySpace] = value
        indexOfEmptySpace++
    }

    private fun resizeArray() {
        maxSize *= 2
        val newBiggerArray = arrayOfNulls<String>(size = maxSize)
        arrayContainer.forEachIndexed() { index, value ->
            newBiggerArray[index] = value
        }
        arrayContainer = newBiggerArray
    }

    fun getSize() = indexOfEmptySpace - 1

    fun print() {
        (0 until indexOfEmptySpace).forEach {
            println(arrayContainer[it])
        }
    }

}