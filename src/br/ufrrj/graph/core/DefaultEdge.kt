package br.ufrrj.graph.core

import br.ufrrj.graph.core.base.Edge
import br.ufrrj.graph.core.base.Vertex
import java.util.*

class DefaultEdge<VertexValueType>(id: String = UUID.randomUUID().toString(),
                                   initialVertex: Vertex<VertexValueType>,
                                   arrivalVertex: Vertex<VertexValueType>,
                                   cost: Double): Edge<VertexValueType>(id, initialVertex, arrivalVertex, cost) {

    override fun contains(vertex: Vertex<VertexValueType>?): Boolean
        = vertex == initialVertex || vertex == arrivalVertex

    override fun toString(): String = "(${initialVertex.id}) --[$cost]--> (${arrivalVertex.id})"
}