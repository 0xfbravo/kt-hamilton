package br.ufrrj.graph.core.base

abstract class Graph<VertexValueType>: Iterable<Vertex<VertexValueType>> {

    abstract val vertices: List<Vertex<VertexValueType>>
    abstract val edges: List<Edge<VertexValueType>>

    /**
     * Gets edges from a certain vertex.
     */
    abstract fun getEdgesFrom(vertex: Vertex<VertexValueType>?): Collection<Edge<VertexValueType>>

    /**
     * Gets edges from a certain vertex.
     */
    abstract fun getEdgesTo(vertex: Vertex<VertexValueType>?): Collection<Edge<VertexValueType>>

    /**
     * Gets a vertex by ID or returns a null value.
     */
    abstract fun getVertexById(id: String): Vertex<VertexValueType>?

    /**
     * Gets all edges between two vertices.
     */
    abstract fun getEdgesByVertex(from: Vertex<VertexValueType>, to: Vertex<VertexValueType>): Collection<Edge<VertexValueType>>

    /**
     * Checks if this graph contains a certain vertex.
     */
    abstract fun contains(vertex: Vertex<VertexValueType>?): Boolean

    /**
     * Checks if this graph contains a certain id.
     */
    abstract fun contains(id: String): Boolean

    /**
     * Checks if this graph contains a certain edge.
     */
    abstract fun contains(edge: Edge<VertexValueType>): Boolean

    override fun toString(): String {
        val verticesString = "[Vertices]\n\t${vertices.joinToString("\n\t") { it.toString() }}"
        val edgesString = "[Edges]\n\t${edges.joinToString("\n\t") { it.toString() }}"
        return "---\nVisual Representation of Graph: ${super.toString()}\n$verticesString\n$edgesString\n---"
    }
}