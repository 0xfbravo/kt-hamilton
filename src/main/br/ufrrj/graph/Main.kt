package br.ufrrj.graph

import br.ufrrj.graph.core.DefaultGraph
import br.ufrrj.graph.core.algorithms.HamiltonianChecker
import br.ufrrj.graph.core.utils.GraphFactory
import br.ufrrj.graph.core.utils.measure
import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    reader.useLocale(Locale.getDefault())
    println("== UFRRJ - Hamiltonian Checker")
    println("Type the number of graph do you want to check")
    println("\t- Simple Graph: 0")
    println("\t- Bipartite Graph: 1")
    println("\t- Complete Graph: 2")

    when (reader.nextInt()) {
        0 -> createSimpleGraphAndCheck(reader)
        1 -> createBipartiteGraphAndCheck(reader)
        2 -> createCompleteGraphAndCheck(reader)
        else -> println("Invalid option... Shutting down")
    }
}

private fun createSimpleGraphAndCheck(reader: Scanner) {
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

    measure {
        println("Creating new graph G(V,E)...")
        graph = GraphFactory.createSimpleGraph("Graph", verticesQuantity, edgesQuantity, vertexInitialValue)
        println(graph)
    }

    measure {
        hamiltonianChecker.withGraph(graph!!, useBruteForce).execute()
    }
}

private fun createBipartiteGraphAndCheck(reader: Scanner) {
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

    measure {
        println("Creating new graph G(V,E)...")
        graph = GraphFactory.createBipartiteGraph("Graph", firstPartitionVerticesQuantity, secondPartitionVerticesQuantity, edgesQuantity, vertexInitialValue)
        println(graph)
    }

    measure {
        hamiltonianChecker.withGraph(graph!!, useBruteForce).execute()
    }
}

private fun createCompleteGraphAndCheck(reader: Scanner) {
    val hamiltonianChecker = HamiltonianChecker<Int>()
    var graph: DefaultGraph<Int>? = null

    println("Creating a Complete graph...")

    print("Type the vertices quantity: ")
    val verticesQuantity = reader.nextInt()

    print("Type the initial value for all vertices: ")
    val vertexInitialValue = reader.nextInt()

    print("Use brute force? (true/false) ")
    val useBruteForce = reader.nextBoolean()

    measure {
        println("Creating new graph G(V,E)...")
        graph = GraphFactory.createCompleteGraph("Graph", verticesQuantity, vertexInitialValue)
        println(graph)
    }

    measure {
        hamiltonianChecker.withGraph(graph!!, useBruteForce).execute()
    }
}