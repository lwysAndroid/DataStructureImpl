package com.example.firstapp.dataStructures.fibonacci

fun main() {
    fib(6).also { println(it) }
}

/**
 * @param positionRequired position between 0 to n
 */
fun fib(
    positionRequired: Int,
    arrayWithPreviousValues: Array<Int> = Array(positionRequired + 1) { 0 }
): Int {
    if (positionRequired == 0 || positionRequired == 1) {
        return 1
    }
    if (arrayWithPreviousValues[positionRequired] == 0) {
        arrayWithPreviousValues[positionRequired] =
            fib(
                positionRequired = positionRequired - 1,
                arrayWithPreviousValues = arrayWithPreviousValues
            ) +
                    fib(
                        positionRequired = positionRequired - 2,
                        arrayWithPreviousValues = arrayWithPreviousValues
                    )
    }
    return arrayWithPreviousValues[positionRequired]
}
