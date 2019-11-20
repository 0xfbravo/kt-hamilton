package br.ufrrj.graph.core

import br.ufrrj.graph.core.base.Edge
import br.ufrrj.graph.core.base.Node
import java.util.*

class DefaultEdge<NodeValueType>(id: String = UUID.randomUUID().toString(),
                                 initialNode: Node<NodeValueType>,
                                 arrivalNode: Node<NodeValueType>,
                                 cost: Double): Edge<NodeValueType>(id, initialNode, arrivalNode, cost) {

    override fun contains(node: Node<NodeValueType>?): Boolean
        = node == initialNode || node == arrivalNode

    override fun toString(): String = "(${initialNode.id}) --[$cost]--> (${arrivalNode.id})"
}