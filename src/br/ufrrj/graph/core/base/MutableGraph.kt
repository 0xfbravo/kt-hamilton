package br.ufrrj.graph.core.base

abstract class MutableGraph<VertexValueType>: Graph<VertexValueType>() {

    /**
     * Adds an new edge to the graph.
     */
    abstract fun addEdge(initialVertex: Vertex<VertexValueType>, arrivalVertex: Vertex<VertexValueType>, cost: Double): Edge<VertexValueType>

    /**
     * Adds a new bidirectional edge to the graph.
     * By default, the cost from arrival vertex to initial vertex
     * is the same of initial vertex to arrival vertex;
     * cost(x->y) == cost(y->x)
     */
    abstract fun addBidirectionalEdge(initialVertex: Vertex<VertexValueType>,
                                      arrivalVertex: Vertex<VertexValueType>,
                                      costInitialToArrival: Double,
                                      costArrivalToInitial: Double = costInitialToArrival): Pair<Edge<VertexValueType>, Edge<VertexValueType>>

    /**
     * Removes a vertex from the graph if it is exists.
     */
    abstract fun removeVertex(id: String): Vertex<VertexValueType>?
    fun removeVertex(vertex: Vertex<VertexValueType>) = removeVertex(vertex.id)

    /**
     * Adds a vertex to the graph with a certain value.
     */
    abstract fun addVertex(value: VertexValueType): Vertex<VertexValueType>

    /**
     * Removes an edge from the graph if it is exists.
     */
    abstract fun removeEdge(id: String): Edge<VertexValueType>?
    fun removeEdge(edge: Edge<VertexValueType>) = removeEdge(edge.id)
}