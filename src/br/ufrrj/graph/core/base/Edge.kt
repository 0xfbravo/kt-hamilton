package br.ufrrj.graph.core.base

abstract class Edge<NodeValueType>(val id: String,
                                   val initialNode: Node<NodeValueType>,
                                   val arrivalNode: Node<NodeValueType>,
                                   val cost: Double) {
    /**
     * Checks if the current edge has access
     * to the node passed as parameter.
     */
    abstract fun contains(node: Node<NodeValueType>?): Boolean

}