package br.ufrrj.graph.core.algorithms

import br.ufrrj.graph.core.base.Graph
import br.ufrrj.graph.core.base.Vertex
import br.ufrrj.graph.core.exceptions.InvalidGraph
import java.util.*
import kotlin.random.Random

/**
 * Hamiltonian Cycle checker
 * @author Fellipe Bravo
 */
class HamiltonianChecker<VertexValueType> {
    var useBruteForce: Boolean = false
    private var graph: Graph<VertexValueType>? = null

    fun withGraph(graph: Graph<VertexValueType>, useBruteForce: Boolean = false): HamiltonianChecker<VertexValueType> {
        this.graph = graph
        this.useBruteForce = useBruteForce
        return this
    }

    fun execute(): Boolean {
        if (graph == null)
            throw InvalidGraph("The graph wasn't defined yet.")

        return isComplete() && isConnected() && isBiconnected()
    }

    /**
     * Necessary condition:
     * Did this graph is connected?
     */
    private fun isConnected(): Boolean {
        graph?.let {
            if (it.edges.isEmpty())
                return false

            return if (useBruteForce) findPathsUsingBruteForce() else findPathsUsingBFS()
        }
        return false
    }

    /**
     * Tries to find paths between all vertices
     * using brute force method
     */
    private fun findPathsUsingBruteForce(): Boolean {
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

    /**
     * Tries to find paths between all vertices
     * using BFS Algorithm.
     */
    private fun findPathsUsingBFS(): Boolean {
        graph?.let {
            val visitController = BooleanArray(it.vertices.size) { false }
            val queue = LinkedList<Vertex<VertexValueType>>()

            val firstVertexIndex = Random.nextInt(it.vertices.size)
            visitController[firstVertexIndex] = true
            queue.add(it.vertices[firstVertexIndex])

            var dequeuedVertex: Vertex<VertexValueType>
            while (queue.isNotEmpty()) {
                dequeuedVertex = queue.poll()
                val neighbors = dequeuedVertex.getNeighbors(it).toList().iterator()
                while (neighbors.hasNext()) {
                    val nextVertex = neighbors.next().arrivalVertex
                    val nextVertexIndex = it.vertices.indexOf(nextVertex)

                    if (!visitController[nextVertexIndex]) {
                        visitController[nextVertexIndex] = true
                        queue.add(nextVertex)
                    }
                }
            }

            return visitController.all { true }
        }

        return false
    }

    /**
     * Necessary condition:
     * Did this graph is biconnected?
     */
    private fun isBiconnected(): Boolean {
        graph?.let {
            if (it.edges.isEmpty())
                return false

            return if (useBruteForce) findArticulationUsingBruteForce() else findArticulationUsingDFS()
        }
        return false
    }

    /**
     * Tries to find Articulation points in this
     * graph using brute force method.
     */
    private fun findArticulationUsingBruteForce(): Boolean {
        graph?.let {
            return true
        }
        return false
    }

    /**
     * Tries to find Articulation points in this
     * graph using DFS Algorithm.
     */
    private fun findArticulationUsingDFS(): Boolean {
        graph?.let {
            return true
        }
        return false
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