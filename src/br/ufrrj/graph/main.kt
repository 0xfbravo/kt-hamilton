package br.ufrrj.graph

import br.ufrrj.graph.core.algorithms.HamiltonianChecker
import br.ufrrj.graph.core.utils.GraphFactory
import br.ufrrj.graph.core.utils.measureTimeMillis

fun main() {
    val hamiltonianChecker = HamiltonianChecker<Int>()

    val simpleGraph = GraphFactory.createSimpleGraph(2, 1, 0)
    val bipartiteGraph = GraphFactory.createBipartiteGraph(2,2, 4, 0)
    val completeGraph = GraphFactory.createCompleteGraph(3, 0)
    println(simpleGraph)
    println(bipartiteGraph)
    println(completeGraph)

    // Measure Simple Graph
    measureTimeMillis({ time -> println("Execution Time: $time") }) {
        println("Simple Graph(V=2,E=1)")
        hamiltonianChecker.withGraph(simpleGraph).execute()
        println("using brute force? ${hamiltonianChecker.useBruteForce}")
    }

    // Measure Bipartite Graph
    measureTimeMillis({ time -> println("Execution Time: $time") }) {
        println("Bipartite Graph(V=4,E=4)")
        hamiltonianChecker.withGraph(bipartiteGraph).execute()
        println("using brute force? ${hamiltonianChecker.useBruteForce}")
    }

    // Measure Complete Graph
    measureTimeMillis({ time -> println("Execution Time: $time") }) {
        println("Simple Graph(V=3,E=1)")
        hamiltonianChecker.withGraph(completeGraph).execute()
        println("using brute force? ${hamiltonianChecker.useBruteForce}")
    }
}