package br.ufrrj.graph.core.base

abstract class Graph<NodeValueType>: Iterable<Node<NodeValueType>> {

    abstract val nodes: Collection<Node<NodeValueType>>
    abstract val edges: Collection<Edge<NodeValueType>>

    /**
     * Gets edges from a certain node.
     */
    abstract fun getEdgesFrom(node: Node<NodeValueType>?): Collection<Edge<NodeValueType>>

    /**
     * Gets a node by ID or returns a null value.
     */
    abstract fun getNodeById(id: String): Node<NodeValueType>?

    /**
     * Gets all edges between two nodes.
     */
    abstract fun getEdgesByNodes(from: Node<NodeValueType>, to: Node<NodeValueType>): Collection<Edge<NodeValueType>>

    /**
     * Checks if this graph contains a certain node.
     */
    abstract fun contains(node: Node<NodeValueType>?): Boolean

    /**
     * Checks if this graph contains a certain id.
     */
    abstract fun contains(id: String): Boolean

    /**
     * Checks if this graph contains a certain edge.
     */
    abstract fun contains(edge: Edge<NodeValueType>): Boolean
}