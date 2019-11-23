package br.ufrrj.graph

import br.ufrrj.graph.core.algorithms.HamiltonianChecker
import br.ufrrj.graph.core.utils.GraphFactory

fun main() {
    val hamiltonianChecker = HamiltonianChecker<Int>()

    val simpleGraph = GraphFactory.createSimpleGraph(2, 1, 10, 0.0)
    val completeGraph = GraphFactory.createCompleteGraph(3, 0, 0.0)
    println(simpleGraph)
    println(completeGraph)

    println(hamiltonianChecker.withGraph(simpleGraph).execute())
    println(hamiltonianChecker.withGraph(completeGraph).execute())
}