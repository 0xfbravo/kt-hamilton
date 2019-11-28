package br.ufrrj.graph.core.algorithms

import br.ufrrj.graph.core.DefaultGraph
import br.ufrrj.graph.core.base.Vertex
import br.ufrrj.graph.core.exceptions.InvalidGraph
import java.util.*
import kotlin.math.min
import kotlin.random.Random

/**
 * Hamiltonian Cycle checker
 * @author Fellipe Bravo
 */
class HamiltonianChecker<VertexValueType> {
    private var useBruteForce: Boolean = false
    private var graph: DefaultGraph<VertexValueType>? = null
    private var timer = 0

    fun withGraph(graph: DefaultGraph<VertexValueType>, useBruteForce: Boolean = false): HamiltonianChecker<VertexValueType> {
        this.graph = graph
        this.useBruteForce = useBruteForce
        return this
    }

    fun execute(): Boolean {
        if (graph == null)
            throw InvalidGraph("The graph wasn't defined yet.")

        println("Using brute force? $useBruteForce")

        /* Checks if the graph is complete - Sufficient Condition */
        val isComplete = isComplete()
        print(if (isComplete) "✓" else "✗")
        println(" ($isComplete)")

        if (!isComplete) {
            println("Is this graph hamiltonian? ✗ ($isComplete)")
            return false
        }

        /* Checks Dirac's Theorem - Sufficient Condition */
        val checkDiracTheorem = checkDiracTheorem()
        print(if (checkDiracTheorem) "✓" else "✗")
        println(" ($checkDiracTheorem)")

        if (!checkDiracTheorem) {
            println("Is this graph hamiltonian? ✗ ($checkDiracTheorem)")
            return false
        }

        /* Checks if the graph is connected - Necessary Condition */
        val isConnected = isConnected()
        print(if (isConnected) "✓" else "✗")
        println(" ($isConnected)")

        /* Checks if the graph is connected - Necessary Condition */
        val isBiconnected = isBiconnected()
        print(if (isBiconnected) "✓" else "✗")
        println(" ($isBiconnected)")

        return true
    }

    /**
     * Necessary condition:
     * Did this graph is connected?
     */
    private fun isConnected(): Boolean {
        print("${if (useBruteForce) "[BRUTEFORCE] " else "[BFS] "}Checking if graph is connected (Necessary Condition)... ")
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
    private fun findPathsUsingBruteForce(): Boolean  {
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
        print("[DFS] Checking if graph is biconnected (Necessary Condition)... ")
        graph?.let {
            if (it.edges.isEmpty())
                return false

            return findArticulationUsingDFS()
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
            val visitController = BooleanArray(it.vertices.size) { false }
            val discoveredTime = IntArray(it.vertices.size) { 0 }
            val low = IntArray(it.vertices.size) { Int.MAX_VALUE }
            val parent = IntArray(it.vertices.size) { -1 }
            val articulationPoints = BooleanArray(it.vertices.size) { false }

            it.vertices.forEachIndexed { index, _ ->
                if (!visitController[index])
                    dfsArticulationPoints(index, visitController, discoveredTime, low, parent, articulationPoints)
            }
            return articulationPoints.all { false }
        }
        return false
    }

    private fun dfsArticulationPoints(currentVertexIndex: Int,
                                      visitController: BooleanArray,
                                      discoveredTime: IntArray,
                                      low: IntArray,
                                      parent: IntArray,
                                      articulationPoints: BooleanArray) {
        graph?.let {
            val currentVertex = it.vertices[currentVertexIndex]
            var children = 0
            visitController[currentVertexIndex] = true
            low[currentVertexIndex] = timer++
            discoveredTime[currentVertexIndex] = low[currentVertexIndex]

            val neighbors = currentVertex.getNeighbors(it).map { edge -> edge.arrivalVertex }.toList().iterator()
            while (neighbors.hasNext()) {
                children++

                val nextVertex = neighbors.next()
                val nextVertexIndex = it.vertices.indexOf(nextVertex)
                if (!visitController[nextVertexIndex]) {
                    parent[nextVertexIndex] = currentVertex.id.toInt()
                    dfsArticulationPoints(nextVertexIndex, visitController, discoveredTime, low, parent, articulationPoints)

                    low[currentVertexIndex] = min(low[currentVertexIndex], low[nextVertexIndex])

                    if (parent[currentVertexIndex] == -1 && children > 1)
                        articulationPoints[currentVertexIndex] = true

                    if (parent[currentVertexIndex] != -1 && low[nextVertexIndex] >= discoveredTime[currentVertexIndex])
                        articulationPoints[currentVertexIndex] = true

                }
                else if (currentVertex.id.toInt() != parent[nextVertexIndex]) {
                    low[currentVertexIndex] = min(low[currentVertexIndex], discoveredTime[nextVertexIndex])
                }
            }
        }
    }

    /**
     * Sufficient condition:
     * Did this graph is Kn complete and n >= 3?
     */
    private fun isComplete(): Boolean {
        print("Checking if graph is complete (Sufficient Condition)... ")
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
        print("Checking of Dirac's Theorem (Sufficient Condition)... ")
        graph?.let {
            if (it.vertices.size < 3)
                return false

            val n = it.vertices.size
            val degrees = it.vertices.map { vertex -> vertex.getNeighbors(it).size }
            val minDegree = degrees.min()!!
            return minDegree >= n/2
        }
        return false
    }

}