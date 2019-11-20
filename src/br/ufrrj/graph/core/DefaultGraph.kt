package br.ufrrj.graph.core

import br.ufrrj.graph.core.base.Edge
import br.ufrrj.graph.core.base.MutableGraph
import br.ufrrj.graph.core.base.Node
import br.ufrrj.graph.core.exceptions.InvalidEdge
import br.ufrrj.graph.core.exceptions.InvalidNode
import java.util.*
import kotlin.collections.HashMap

class DefaultGraph<NodeValueType>: MutableGraph<NodeValueType>() {

    private val nodesMap = HashMap<String, Node<NodeValueType>>()
    private val edgesMap = HashMap<String, Edge<NodeValueType>>()

    override val nodes: Collection<Node<NodeValueType>>
        get() = nodesMap.values.toList()
    override val edges: Collection<Edge<NodeValueType>>
        get() = edgesMap.values.toList()

    override fun iterator(): Iterator<Node<NodeValueType>> = nodesMap.values.iterator()

    // Functions to remove nodes and edges
    override fun removeNode(id: String): Node<NodeValueType>? = nodesMap.remove(id)
    override fun removeEdge(id: String): Edge<NodeValueType>? = edgesMap.remove(id)

    // Functions to check if this graph contains an certain id, node or edge
    override fun contains(id: String): Boolean = id in nodesMap.keys || id in edgesMap.keys
    override fun contains(node: Node<NodeValueType>?): Boolean = node in nodesMap.values
    override fun contains(edge: Edge<NodeValueType>): Boolean = edge in edgesMap.values

    // Functions to add nodes and edges to this graph
    @Throws(InvalidNode::class)
    override fun addNode(value: NodeValueType): Node<NodeValueType> {
        val newId = UUID.randomUUID().toString()

        if (newId in nodesMap.keys)
            throw InvalidNode("The node was already inserted in this graph.")

        DefaultNode(newId, value).apply {
            nodesMap[newId] = this
            return this
        }
    }

    @Throws(InvalidEdge::class)
    override fun addEdge(initialNode: Node<NodeValueType>, arrivalNode: Node<NodeValueType>, cost: Double): Edge<NodeValueType> {
        if (initialNode !in this || arrivalNode !in this)
            throw InvalidEdge("One of the nodes that you're trying to insert aren't in the graph")

        val newId = UUID.randomUUID().toString()

        if (newId in edgesMap.keys)
            throw InvalidEdge("The edge was already inserted in this graph.")

        DefaultEdge(newId, initialNode, arrivalNode, cost).apply {
            edgesMap[newId] = this
            return this
        }
    }

    override fun addBidirectionalEdge(initialNode: Node<NodeValueType>, arrivalNode: Node<NodeValueType>,
                                      costInitialToArrival: Double, costArrivalToInitial: Double): Pair<Edge<NodeValueType>, Edge<NodeValueType>>
            = Pair(addEdge(initialNode, arrivalNode, costArrivalToInitial), addEdge(arrivalNode, initialNode, costArrivalToInitial))

    override fun getNodeById(id: String): Node<NodeValueType>? = nodesMap[id]
    override fun getEdgesByNodes(from: Node<NodeValueType>, to: Node<NodeValueType>): Collection<Edge<NodeValueType>>
            = edgesMap.values.filter { it.initialNode == from && it.arrivalNode == to }
    override fun getEdgesFrom(node: Node<NodeValueType>?): Collection<Edge<NodeValueType>>
            = edgesMap.values.filter { it.initialNode == node }
}