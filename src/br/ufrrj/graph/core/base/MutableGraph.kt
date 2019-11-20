package br.ufrrj.graph.core.base

abstract class MutableGraph<NodeValueType>: Graph<NodeValueType>() {

    /**
     * Adds an new edge to the graph.
     */
    abstract fun addEdge(initialNode: Node<NodeValueType>, arrivalNode: Node<NodeValueType>, cost: Double): Edge<NodeValueType>

    /**
     * Adds a new bidirectional edge to the graph.
     * By default, the cost from arrival node to initial node
     * is the same of initial node to arrival node;
     * cost(x->y) == cost(y->x)
     */
    abstract fun addBidirectionalEdge(initialNode: Node<NodeValueType>,
                                      arrivalNode: Node<NodeValueType>,
                                      costInitialToArrival: Double,
                                      costArrivalToInitial: Double = costInitialToArrival): Pair<Edge<NodeValueType>, Edge<NodeValueType>>

    /**
     * Removes a node from the graph if it is exists.
     */
    abstract fun removeNode(id: String): Node<NodeValueType>?
    fun removeNode(node: Node<NodeValueType>) = removeNode(node.id)

    /**
     * Adds a node to the graph with a certain value.
     */
    abstract fun addNode(value: NodeValueType): Node<NodeValueType>

    /**
     * Removes an edge from the graph if it is exists.
     */
    abstract fun removeEdge(id: String): Edge<NodeValueType>?
    fun removeEdge(edge: Edge<NodeValueType>) = removeEdge(edge.id)
}