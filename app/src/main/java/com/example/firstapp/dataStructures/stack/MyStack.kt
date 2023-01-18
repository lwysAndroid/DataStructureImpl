package com.example.firstapp.dataStructures.stack

class MyStack<T> {

    private val emptyStackMessage = "The Stack is empty"

    private class MyStackNode<T>(var value: T) {
        var next: MyStackNode<T>? = null
    }

    private var top: MyStackNode<T>? = null

    fun isEmpty(): Boolean {
        return top == null
    }

    fun push(item: T) {
        val newNode = MyStackNode(value = item)
        if (isEmpty()) {
            top = newNode
            return
        }

        val previousTop = top
        top = newNode
        top!!.next = previousTop
    }

    fun peek(): T {
        return top?.value ?: throw Exception()//emptyStackMessage
    }

    fun pop(): T {
        if (isEmpty()) {
            return throw Exception()//emptyStackMessage
        }

        val topValue = top!!.value
        val newTop = top!!.next
        top = newTop
        return topValue
    }
}