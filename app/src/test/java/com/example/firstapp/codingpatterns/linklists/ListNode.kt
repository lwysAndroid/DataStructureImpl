package com.example.firstapp.codingpatterns.linklists

data class ListNode<T>(val value: T) {
    var next: ListNode<T>? = null
}

fun createLinkedListFromArray(array: Array<Int>): ListNode<Int>? {
    var previousListNode: ListNode<Int>? = null
    var head: ListNode<Int>? = null
    array.forEach { value ->
        val currentNode = ListNode(value = value)
        if (previousListNode == null) {
            head = currentNode
        }
        previousListNode?.next = currentNode
        previousListNode = currentNode
    }
    return head
}

fun printLinkedList(head: ListNode<Int>?) {
    if (head == null) {
        println()
        return
    }
    val value = head.value
    val next = head.next
    val message = if (next != null) {
        "$value,"
    } else {
        "$value"
    }
    print(message)
    printLinkedList(head = next)
}
