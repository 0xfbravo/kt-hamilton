package br.ufrrj.graph.core.base

abstract class Vertex<VertexValueType>(val id: String,
                                     var value: VertexValueType? = null) {

    /**
     * Returns all the edges reachable from
     * this vertex.
     */
    fun getEdges(graph: Graph<VertexValueType>) = graph.getEdgesFrom(this)

    /**
     * Checks if this vertex value is equal
     * to a certain value
     */
    fun contains(value: VertexValueType?) = value == value

    override fun toString(): String = "${this.javaClass.canonicalName} :: ID = ${this.id} | Value = ${this.value}"
}