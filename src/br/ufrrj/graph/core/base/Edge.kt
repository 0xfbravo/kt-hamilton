package br.ufrrj.graph.core.base

abstract class Edge<VertexValueType>(val id: String,
                                   val initialVertex: Vertex<VertexValueType>,
                                   val arrivalVertex: Vertex<VertexValueType>,
                                   val cost: Double) {
    /**
     * Checks if the current edge has access
     * to the vertex passed as parameter.
     */
    abstract fun contains(vertex: Vertex<VertexValueType>?): Boolean

    override fun toString(): String = "(${initialVertex.id}) --[$cost]--> (${arrivalVertex.id})"
}