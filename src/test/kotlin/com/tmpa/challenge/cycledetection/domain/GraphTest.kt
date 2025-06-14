package com.tmpa.challenge.cycledetection.domain
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GraphTest {

    @Test
    fun `findCycleEdges returns empty list for acyclic graph`() {
        val graph = Graph()
        graph.addNode("A")
        graph.addNode("B")
        graph.addNode("C")
        graph.addEdge(Edge(Node("A"), Node("B")))
        graph.addEdge(Edge(Node("B"), Node("C")))

        val cycleEdges = graph.findCycleEdges()
        assertTrue(cycleEdges.isEmpty())
    }

    @Test
    fun `findCycleEdges detects a single cycle`() {
        val graph = Graph()
        graph.addNode("A")
        graph.addNode("B")
        graph.addNode("C")
        graph.addEdge(Edge(Node("A"), Node("B")))
        graph.addEdge(Edge(Node("B"), Node("C")))
        graph.addEdge(Edge(Node("C"), Node("A")))

        val cycleEdges = graph.findCycleEdges()
        assertFalse(cycleEdges.isEmpty())
        // There should be at least one edge that closes the cycle
        assertTrue(cycleEdges.any {
            (it.from.id == "C" && it.to.id == "A") || (it.from.id == "A" && it.to.id == "C")
        })
    }

    @Test
    fun `findCycleEdges detects multiple cycles`() {
        val graph = Graph()
        graph.addNode("A")
        graph.addNode("B")
        graph.addNode("C")
        graph.addNode("D")
        graph.addEdge(Edge(Node("A"), Node("B")))
        graph.addEdge(Edge(Node("B"), Node("C")))
        graph.addEdge(Edge(Node("C"), Node("A"))) // First cycle
        graph.addEdge(Edge(Node("C"), Node("D")))
        graph.addEdge(Edge(Node("D"), Node("B"))) // Second cycle

        val cycleEdges = graph.findCycleEdges()
        assertTrue(cycleEdges.size >= 2)
        // Check that at least two different cycle edges are present
        val edgeSet = cycleEdges.map { setOf(it.from.id, it.to.id) }.toSet()
        assertTrue(edgeSet.contains(setOf("C", "A")))
        assertTrue(edgeSet.contains(setOf("D", "B")))
    }
}
