package br.ufrrj.graph.core

import br.ufrrj.graph.core.base.Edge
import br.ufrrj.graph.core.base.MutableGraph
import br.ufrrj.graph.core.base.Vertex
import br.ufrrj.graph.core.exceptions.InvalidEdge
import br.ufrrj.graph.core.exceptions.InvalidVertex
import java.util.*
import kotlin.collections.HashMap

class DefaultGraph<VertexValueType>: MutableGraph<VertexValueType>() {

    private var verticesCount = 0
    private var edgeCount = 0

    private val vertexMap = HashMap<String, Vertex<VertexValueType>>()
    private val edgesMap = HashMap<String, Edge<VertexValueType>>()

    override val vertices: List<Vertex<VertexValueType>>
        get() = vertexMap.values.toList()
    override val edges: List<Edge<VertexValueType>>
        get() = edgesMap.values.toList()

    override fun iterator(): Iterator<Vertex<VertexValueType>> = vertexMap.values.iterator()

    // Functions to remove vertices and edges
    override fun removeVertex(id: String): Vertex<VertexValueType>? = vertexMap.remove(id)
    override fun removeEdge(id: String): Edge<VertexValueType>? = edgesMap.remove(id)

    // Functions to check if this graph contains an certain id, vertex or edge
    override fun contains(id: String): Boolean = id in vertexMap.keys || id in edgesMap.keys
    override fun contains(vertex: Vertex<VertexValueType>?): Boolean = vertex in vertexMap.values
    override fun contains(edge: Edge<VertexValueType>): Boolean = edge in edgesMap.values

    // Functions to add vertices and edges to this graph
    @Throws(InvalidVertex::class)
    override fun addVertex(value: VertexValueType): Vertex<VertexValueType> {
        val newId = verticesCount.toString()
        if (newId in vertexMap.keys)
            throw InvalidVertex("The vertex was already inserted in this graph.")

        verticesCount++
        DefaultVertex(newId, value).apply {
            vertexMap[newId] = this
            return this
        }
    }

    @Throws(InvalidEdge::class)
    override fun addEdge(initialVertex: Vertex<VertexValueType>, arrivalVertex: Vertex<VertexValueType>, cost: Double): Edge<VertexValueType> {
        if (initialVertex !in this || arrivalVertex !in this)
            throw InvalidEdge("One of the vertices that you're trying to insert aren't in the graph")

        if (getEdgesFrom(initialVertex).any { it.contains(arrivalVertex) } || getEdgesFrom(arrivalVertex).any { it.contains(initialVertex) })
            throw InvalidEdge("The edge was already inserted in this graph.")

        val newId = edgeCount.toString()
        if (newId in edgesMap.keys)
            throw InvalidEdge("The edge was already inserted in this graph.")

        edgeCount++
        DefaultEdge(newId, initialVertex, arrivalVertex, cost).apply {
            edgesMap[newId] = this
            return this
        }
    }

    override fun addBidirectionalEdge(initialVertex: Vertex<VertexValueType>, arrivalVertex: Vertex<VertexValueType>,
                                      costInitialToArrival: Double, costArrivalToInitial: Double): Pair<Edge<VertexValueType>, Edge<VertexValueType>>
            = Pair(addEdge(initialVertex, arrivalVertex, costArrivalToInitial), addEdge(arrivalVertex, initialVertex, costArrivalToInitial))

    override fun getVertexById(id: String): Vertex<VertexValueType>? = vertexMap[id]
    override fun getEdgesByVertex(from: Vertex<VertexValueType>, to: Vertex<VertexValueType>): Collection<Edge<VertexValueType>>
            = edgesMap.values.filter { it.initialVertex == from && it.arrivalVertex == to }
    override fun getEdgesFrom(vertex: Vertex<VertexValueType>?): Collection<Edge<VertexValueType>>
            = edgesMap.values.filter { it.initialVertex == vertex }
    override fun getEdgesTo(vertex: Vertex<VertexValueType>?): Collection<Edge<VertexValueType>>
            = edgesMap.values.filter { it.arrivalVertex == vertex }
}