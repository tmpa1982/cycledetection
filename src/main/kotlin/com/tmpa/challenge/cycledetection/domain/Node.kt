package com.tmpa.challenge.cycledetection.domain

class Node(val id: String) {
    private val edges: MutableList<Node> = mutableListOf()

    fun addEdge(node: Node) {
        edges.add(node)
    }

    fun getEdges(): List<Node> {
        return edges
    }

    override fun toString(): String {
        return "Node(id='$id', edges=${edges.map { it.id }})"
    }
}
