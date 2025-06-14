package com.tmpa.challenge.cycledetection.domain

class Edge(val from: Node, val to: Node) {

    override fun toString(): String {
        return "Edge(from=${from.id}, to=${to.id})"
    }
}
