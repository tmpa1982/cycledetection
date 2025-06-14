package com.tmpa.challenge.cycledetection.domain

class Graph {
    private val nodes: MutableMap<String, Node> = mutableMapOf()

    fun addNode(id: String) {
        if (!nodes.containsKey(id)) {
            nodes[id] = Node(id)
        }
    }

    fun addEdge(edge: Edge) {
        val fromNode = nodes[edge.from.id] ?: throw IllegalArgumentException("Node ${edge.from.id} does not exist")
        val toNode = nodes[edge.to.id] ?: throw IllegalArgumentException("Node ${edge.to.id} does not exist")
        fromNode.addEdge(toNode)
    }

    fun getNodes(): List<Node> {
        return nodes.values.toList()
    }
}