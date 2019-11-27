package br.ufrrj.graph

import br.ufrrj.graph.core.DefaultGraph
import br.ufrrj.graph.core.algorithms.HamiltonianChecker
import br.ufrrj.graph.core.utils.GraphFactory
import br.ufrrj.graph.core.utils.measureTimeMillis
import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    println("== UFRRJ - Hamiltonian Checker")
    println("Type the number of graph do you want to check")
    println("\t- Simple Graph: 0")
    println("\t- Bipartite Graph: 1")
    println("\t- Complete Graph: 2")

    when (reader.nextInt()) {
        0 -> createSimpleGraphAndCheck()
        1 -> createBipartiteGraphAndCheck()
        2 -> createCompleteGraphAndCheck()
        else -> println("Invalid option... Shutting down")
    }
}

private fun createSimpleGraphAndCheck() {
    val reader = Scanner(System.`in`)
    val hamiltonianChecker = HamiltonianChecker<Int>()
    var graph: DefaultGraph<Int>? = null

    println("Creating a simple graph...")

    print("Type the vertices quantity: ")
    val verticesQuantity = reader.nextInt()

    print("Type the edges quantity: ")
    val edgesQuantity = reader.nextInt()

    print("Type the initial value for all vertices: ")
    val vertexInitialValue = reader.nextInt()

    print("Use brute force? (true/false) ")
    val useBruteForce = reader.nextBoolean()

    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        println("Creating new graph G(V,E)...")
        graph = GraphFactory.createSimpleGraph("Graph", verticesQuantity, edgesQuantity, vertexInitialValue)
        println(graph)
    }

    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        hamiltonianChecker.withGraph(graph!!, useBruteForce).execute()
    }
}

private fun createBipartiteGraphAndCheck() {
    val reader = Scanner(System.`in`)
    val hamiltonianChecker = HamiltonianChecker<Int>()
    var graph: DefaultGraph<Int>? = null

    println("Creating a Bipartite graph...")

    print("Type the first partition vertices quantity: ")
    val firstPartitionVerticesQuantity = reader.nextInt()

    print("Type the second partition vertices quantity: ")
    val secondPartitionVerticesQuantity = reader.nextInt()

    print("Type edges quantity: ")
    val edgesQuantity = reader.nextInt()

    print("Type the initial value for all vertices: ")
    val vertexInitialValue = reader.nextInt()

    print("Use brute force? (true/false) ")
    val useBruteForce = reader.nextBoolean()

    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        println("Creating new graph G(V,E)...")
        graph = GraphFactory.createBipartiteGraph("Graph", firstPartitionVerticesQuantity, secondPartitionVerticesQuantity, edgesQuantity, vertexInitialValue)
        println(graph)
    }

    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        hamiltonianChecker.withGraph(graph!!, useBruteForce).execute()
    }
}

private fun createCompleteGraphAndCheck() {
    val reader = Scanner(System.`in`)
    val hamiltonianChecker = HamiltonianChecker<Int>()
    var graph: DefaultGraph<Int>? = null

    println("Creating a Complete graph...")

    print("Type the vertices quantity: ")
    val verticesQuantity = reader.nextInt()

    print("Type the initial value for all vertices: ")
    val vertexInitialValue = reader.nextInt()

    print("Use brute force? (true/false) ")
    val useBruteForce = reader.nextBoolean()

    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        println("Creating new graph G(V,E)...")
        graph = GraphFactory.createCompleteGraph("Graph", verticesQuantity, vertexInitialValue)
        println(graph)
    }

    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        hamiltonianChecker.withGraph(graph!!, useBruteForce).execute()
    }
}