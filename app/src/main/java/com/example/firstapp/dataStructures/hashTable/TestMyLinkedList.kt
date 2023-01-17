package com.example.firstapp.dataStructures.hashTable

fun main() {
    val myLinkedList = MyLinkedList()
    testNodesList.forEach { myLinkedList.add(it) }
//    myLinkedList.getAllNodes().forEach { println(it) }
    myLinkedList.find("20").also { println(it) }
}

val testNodesList = (1 until 4).map {
    Node(key = it.toString(), value = it.toString())
}