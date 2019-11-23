package br.ufrrj.graph

fun main(args: Array<String>) {
    val simpleGraph = GraphFactory.createSimpleGraph(2, 5, 10, 0.0)
    val completeGraph = GraphFactory.createCompleteGraph(5, 0, 0.0)
    println(simpleGraph)
    println(completeGraph)
}