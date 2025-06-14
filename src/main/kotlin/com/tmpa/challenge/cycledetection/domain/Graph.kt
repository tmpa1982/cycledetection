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

    fun findCycleEdges(): List<Edge> {
        val visited = mutableSetOf<Node>()
        val stack = mutableSetOf<Node>()
        val cycleEdges = mutableListOf<Edge>()

        fun dfs(node: Node, parent: Node?): Boolean {
            visited.add(node)
            stack.add(node)
            for (neighbor in node.getEdges()) {
                if (neighbor == parent) continue
                if (!visited.contains(neighbor)) {
                    if (dfs(neighbor, node)) {
                        if (stack.contains(neighbor)) {
                            cycleEdges.add(Edge(node, neighbor))
                        }
                        return true
                    }
                } else if (stack.contains(neighbor)) {
                    cycleEdges.add(Edge(node, neighbor))
                    return true
                }
            }
            stack.remove(node)
            return false
        }

        for (node in nodes.values) {
            if (!visited.contains(node)) {
                dfs(node, null)
            }
        }
        return cycleEdges
    }
}
