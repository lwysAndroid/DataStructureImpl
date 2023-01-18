package com.example.firstapp.dataStructures.queue

fun main() {
    val myQueue = MyQueue<Int>()
    println("Is empty: ${myQueue.isEmpty()}")
    (0..5).forEach { myQueue.add(item = it) }
    println("Is empty: ${myQueue.isEmpty()}")
    println("Peek: ${myQueue.peek()}")
    println("Remove: ${myQueue.remove()}")
    println("Remove: ${myQueue.remove()}")
    println("Remove: ${myQueue.remove()}")
}