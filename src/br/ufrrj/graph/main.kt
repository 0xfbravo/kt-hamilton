package br.ufrrj.graph

import br.ufrrj.graph.core.utils.GraphFactory

fun main(args: Array<String>) {
    val simpleGraph = GraphFactory.createSimpleGraph(2, 1, 10, 0.0)
    val completeGraph = GraphFactory.createCompleteGraph(5, 0, 0.0)
    println(simpleGraph)
    println(completeGraph)
}