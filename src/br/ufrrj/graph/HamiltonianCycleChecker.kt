package br.ufrrj.graph

import br.ufrrj.graph.core.base.Graph

/**
 * Hamiltonian Cycle checker
 * @author Fellipe Bravo
 */
class HamiltonianCycleChecker<VertexValueType>(private val graph: Graph<VertexValueType>) {

    /**
     * Checks if there's hamiltonian cycle
     * using the brute force method.
     */
    fun usingBruteForce() {
        TODO("Method not implemented yet")
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
     * Dirac's Theorem (1952)
     */
    private fun checkDiracTheorem(): Boolean {
        TODO("Method not implemented yet")
    }

    /**
     * Sufficient condition:
     * Did this graph is Kn complete and n >= 3?
     */
    private fun checkIfGraphIsComplete() {
        TODO("Method not implemented yet")
    }

}