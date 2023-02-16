package com.example.firstapp.algoexpert

fun spiralTraverse(array: List<List<Int>>): List<Int> {
    var indexLeft = 0
    var indexRight = array[0].size - 1
    var indexTop = 0
    var indexBottom = array.size - 1

    val endSize = array[0].size * array.size
    val resultArray = ArrayList<Int>()
    var direction = Direction.Right

    while (resultArray.size < endSize) {

        when (direction) {
            Direction.Right -> {
                val currentRow = array[indexTop]
                for (index in indexLeft..indexRight) {
                    resultArray.add(currentRow[index])
                }
                indexTop++
                direction = Direction.Bottom
            }
            Direction.Bottom -> {
                for (index in indexTop..indexBottom) {
                    val currentRow = array[index]
                    resultArray.add(currentRow[indexRight])
                }
                indexRight--
                direction = Direction.Left
            }
            Direction.Left -> {
                val currentRow = array[indexBottom]
                for (index in indexRight downTo indexLeft) {
                    resultArray.add(currentRow[index])
                }
                indexBottom--
                direction = Direction.Top
            }
            Direction.Top -> {
                for (index in indexBottom downTo indexTop) {
                    val currentRow = array[index]
                    resultArray.add(currentRow[indexLeft])
                }
                indexLeft++
                direction = Direction.Right
            }
        }
    }

    return resultArray
}

enum class Direction {
    Right, Bottom, Left, Top
}

fun testSpiralTraverse() {
    val arrayToTest = mutableListOf(
        mutableListOf(1, 2, 3, 4),
        mutableListOf(12, 13, 14, 5),
        mutableListOf(11, 16, 15, 6),
        mutableListOf(10, 9, 8, 7),
    )
    spiralTraverse(array = arrayToTest).forEach{ print("$it, ") }
}
