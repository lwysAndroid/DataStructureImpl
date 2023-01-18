package com.example.firstapp.dataStructures.stack

fun main() {

    val myStack = MyStack<Int>()
    println("Is Empty: ${myStack.isEmpty()}")
    (0..5).forEach { myStack.push(item = it) }
    println("Is Empty: ${myStack.isEmpty()}")
    println("Peek: ${myStack.peek()}")
    println("Poop: ${myStack.pop()}")
    println("Peek: ${myStack.peek()}")
}