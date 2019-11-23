package br.ufrrj.graph

import br.ufrrj.graph.core.algorithms.HamiltonianChecker
import br.ufrrj.graph.core.utils.GraphFactory
import br.ufrrj.graph.core.utils.measureTimeMillis

fun main() {
    val hamiltonianChecker = HamiltonianChecker<Int>()

    // Measure Simple Graph
    val simpleGraph = GraphFactory.createSimpleGraph("Simple Graph", 4, 1, 0)
    println(simpleGraph)
    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        println("Simple Graph(V=2,E=1)")
        println("using brute force? ${hamiltonianChecker.useBruteForce}")
        println("is hamiltonian? ${hamiltonianChecker.withGraph(simpleGraph).execute()}")
    }

    // Measure Bipartite Graph
    val bipartiteGraph = GraphFactory.createBipartiteGraph("Bipartite Graph", 2,2, 4, 0)
    println(bipartiteGraph)
    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        println("Bipartite Graph(V=4,E=4)")
        println("using brute force? ${hamiltonianChecker.useBruteForce}")
        println("is hamiltonian? ${hamiltonianChecker.withGraph(bipartiteGraph).execute()}")
    }

    // Measure Complete Graph
    val completeGraph = GraphFactory.createCompleteGraph("Complete Graph",100, 0)
    println(completeGraph)
    measureTimeMillis({ time -> println("Execution Time: ${time}ms") }) {
        println("using brute force? ${hamiltonianChecker.useBruteForce}")
        println("is hamiltonian? ${hamiltonianChecker.withGraph(completeGraph).execute()}")
    }
}