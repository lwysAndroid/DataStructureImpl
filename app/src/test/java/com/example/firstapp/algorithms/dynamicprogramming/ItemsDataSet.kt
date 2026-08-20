package com.example.firstapp.algorithms.dynamicprogramming

const val campingAvailableWeight = 6F

fun getCampingItems(): Set<Item> {
    val items: MutableSet<Item> = mutableSetOf(
        Item(name = "Water", weight = 3F, worthValue = 10F),
        Item(name = "Book", weight = 1F, worthValue = 3F),
        Item(name = "Food", weight = 2F, worthValue = 9F),
        Item(name = "Jacket", weight = 2F, worthValue = 5F),
        Item(name = "Camera", weight = 1F, worthValue = 6F),
    )
    return items
}

const val storeAvailableWeight = 4F

fun getStoreItems(): Set<Item> {
    val items: MutableSet<Item> = mutableSetOf(
        Item(name = "Stereo", weight = 4F, worthValue = 3000F),
        Item(name = "Laptop", weight = 3F, worthValue = 2000F),
        Item(name = "Guitar", weight = 1F, worthValue = 1500F),

        Item(name = "iPhone", weight = 1F, worthValue = 2000F),
//        Item(name = "Necklace", weight = 0.5F, worthValue = 1000F),
        Item(name = "Diamond", weight = 3.5F, worthValue = 1_000_000F),
    )
    return items
}

const val travelAvailableWeight = 2F

fun getTravelItems(): Set<Item> {
    val items: MutableSet<Item> = mutableSetOf(
        Item(name = "Westminster Abbey", weight = 0.5F, worthValue = 7F),
        Item(name = "Globe Theater", weight = 0.5F, worthValue = 6F),
        Item(name = "National Gallery", weight = 1F, worthValue = 9F),
        Item(name = "British Museum", weight = 2F, worthValue = 9F),
        Item(name = "St. Paul's Cathedral", weight = 0.5F, worthValue = 8F),
    )
    return items
}

