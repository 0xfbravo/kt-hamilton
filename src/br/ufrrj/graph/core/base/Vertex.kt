package br.ufrrj.graph.core.base

abstract class Node<NodeValueType>(val id: String,
                                   var value: NodeValueType? = null) {

    /**
     * Returns all the edges reachable from
     * this node.
     */
    fun getEdges(graph: Graph<NodeValueType>) = graph.getEdgesFrom(this)

    /**
     * Checks if this node value is equal
     * to a certain value
     */
    fun contains(value: NodeValueType?) = value == value

    override fun toString(): String = "${this.javaClass.canonicalName} :: ID = ${this.id} | Value = ${this.value}"
}