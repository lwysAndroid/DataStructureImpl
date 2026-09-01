package com.example.firstapp.codingpatterns.graphs

data class GraphNode(val value: Int) {
    var array: Array<GraphNode>? = null
}