package com.example.firstapp.dataStructures.queue

class MyQueue<T> {

    class MyQueueNode<T>(var value: T) {
        var next: MyQueueNode<T>? = null
    }

    private var first: MyQueueNode<T>? = null
    private var last: MyQueueNode<T>? = null

    fun isEmpty(): Boolean {
        return first == null
    }

    fun peek(): T {
        return first?.value ?: throw Exception()
    }

    fun add(item: T) {
        val addedNode = MyQueueNode(value = item)
        if (isEmpty()) {
            first = addedNode
            last = addedNode
            return
        }

        last!!.next = addedNode
        last = addedNode
    }

    fun remove(): T {
        if (isEmpty()) {
            throw Exception()
        }

        val value = first!!.value
        if (first == last) {
            first = null
            last = null
        } else {
            first = first!!.next
        }

        return value
    }

}