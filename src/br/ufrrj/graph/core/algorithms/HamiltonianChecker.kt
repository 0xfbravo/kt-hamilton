package br.ufrrj.graph.core.algorithms

import br.ufrrj.graph.core.base.Graph
import br.ufrrj.graph.core.exceptions.InvalidGraph

/**
 * Hamiltonian Cycle checker
 * @author Fellipe Bravo
 */
class HamiltonianChecker<VertexValueType>(private val useBruteForce: Boolean = false) {
    private var graph: Graph<VertexValueType>? = null

    fun withGraph(graph: Graph<VertexValueType>): HamiltonianChecker<VertexValueType> {
        this.graph = graph
        return this
    }

    fun execute(): Boolean {
        if (graph == null)
            throw InvalidGraph("The graph wasn't defined yet.")

        return isComplete()
    }

    /**
     * Necessary condition:
     * Did this graph is connected?
     */
    private fun isConnected(): Boolean {
        TODO("Method not implemented yet")
    }

    /**
     * Necessary condition:
     * Did this graph is biconnected?
     */
    private fun isBiconnected(): Boolean {
        TODO("Method not implemented yet")
    }

    /**
     * Sufficient condition:
     * Did this graph is Kn complete and n >= 3?
     */
    private fun isComplete(): Boolean {
        graph?.let {
            if (it.vertices.size < 3)
                return false

            val n = it.vertices.size
            return ((n * (n - 1)) / 2) == it.edges.size
        }

        return false
    }

    /**
     * Sufficient condition:
     * Dirac's Theorem (1952)
     */
    private fun checkDiracTheorem(): Boolean {
        TODO("Method not implemented yet")
    }

}