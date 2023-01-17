package com.example.firstapp.dataStructures.dynamicArray

fun main() {
    val dynamicArraySize = 10
    val dynamicArray = DynamicArray(maxSize = dynamicArraySize)
    (0 until dynamicArraySize*3).forEach {
        dynamicArray.add("list $it")
    }
    dynamicArray.print()
    dynamicArray.getSize().also { println(it) }
}