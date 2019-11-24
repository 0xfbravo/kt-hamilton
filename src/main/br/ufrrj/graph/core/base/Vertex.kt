package br.ufrrj.graph.core.base

abstract class Vertex<VertexValueType>(val id: String,
                                       var value: VertexValueType? = null) {

    /**
     * Returns all the vertices reachable from
     * this vertex.
     */
    fun getNeighbors(graph: Graph<VertexValueType>) = (graph.getEdgesFrom(this) + graph.getEdgesTo(this))

    /**
     * Checks if this vertex value is equal
     * to a certain value
     */
    fun contains(value: VertexValueType?) = value == value

    override fun toString(): String = "(ID = ${this.id} | Value = ${this.value})"
}