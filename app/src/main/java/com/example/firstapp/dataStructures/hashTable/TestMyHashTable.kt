package com.example.firstapp.dataStructures.hashTable

fun main(){
    val hashTable =MyHashTable()
    with(hashTable){
        add("A","Car")
        add("AB","Bar")
        add("ABC","Mar")
        add("ABCD","Far")
    }
    hashTable.getValue("ABCDE").also { println(it) }
}