package br.ufrrj.graph.core.algorithms

import br.ufrrj.graph.core.base.Graph
import br.ufrrj.graph.core.exceptions.InvalidGraph

/**
 * Hamiltonian Cycle checker
 * @author Fellipe Bravo
 */
class HamiltonianChecker<VertexValueType> {
    var useBruteForce: Boolean = true
    private var graph: Graph<VertexValueType>? = null

    fun withGraph(graph: Graph<VertexValueType>, useBruteForce: Boolean = true): HamiltonianChecker<VertexValueType> {
        this.graph = graph
        this.useBruteForce = useBruteForce
        return this
    }

    fun execute(): Boolean {
        if (graph == null)
            throw InvalidGraph("The graph wasn't defined yet.")

        return isConnected() && isComplete()
    }

    /**
     * Necessary condition:
     * Did this graph is connected?
     */
    private fun isConnected(): Boolean {
        graph?.let {
            if (it.edges.isEmpty())
                return false

            return if (useBruteForce) findPathsBruteForce() else findPathsOptimized()
        }

        return false
    }

    private fun findPathsBruteForce(): Boolean {
        graph?.let {
            for (initialVertexIndex in it.vertices.indices) {
                val initialVertex = it.vertices[initialVertexIndex]
                for (arrivalVertexIndex in (initialVertexIndex+1 until it.vertices.size)) {
                    val arrivalVertex = it.vertices[arrivalVertexIndex]
                    val edgesBetween = it.getEdgesByVertex(initialVertex, arrivalVertex)
                    if (edgesBetween.isEmpty())
                        return false
                }
            }
            return true
        }
        return false
    }

    private fun findPathsOptimized(): Boolean {
        return true
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