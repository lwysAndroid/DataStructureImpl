package com.example.firstapp.dataStructures.linkedList

fun main() {
    val myLinkedList = MyLinkedList()
    testNodesList.forEach { myLinkedList.add(it) }
    myLinkedList.getAllNodes().forEach { println(it) }
    println("-----------------------------")
    myLinkedList.reverse()
    myLinkedList.getAllNodes().forEach { println(it) }
}

val testNodesList = (1 until 4).map {
    Node(key = it.toString(), value = it.toString())
}