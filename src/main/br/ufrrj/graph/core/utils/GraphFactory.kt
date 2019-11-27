package br.ufrrj.graph.core.utils

import br.ufrrj.graph.core.DefaultGraph
import br.ufrrj.graph.core.exceptions.InvalidEdge
import br.ufrrj.graph.core.exceptions.InvalidVertex
import kotlin.random.Random

object GraphFactory {

    @Throws(InvalidVertex::class, InvalidEdge::class)
    fun <VertexValueType> createSimpleGraph(graphName:String,
                                            verticesQuantity: Int,
                                            edgesQuantity: Int,
                                            vertexInitialValue: VertexValueType,
                                            edgeInitialCost: Double = 0.0): DefaultGraph<VertexValueType> {
        if (edgesQuantity > (verticesQuantity * (verticesQuantity - 1) / 2))
            throw InvalidEdge("Too many edges")

        if (edgesQuantity < 0)
            throw InvalidEdge("Too few edges")

        val newGraph = DefaultGraph<VertexValueType>(graphName)

        /* Creates all vertices in graph */
        while (newGraph.vertices.size < verticesQuantity) {
            newGraph.addVertex(vertexInitialValue)
        }

        /* Creates random edges between vertices */
        while (newGraph.edges.size < edgesQuantity) {
            val randomInitialVertex = newGraph.vertices[Random.nextInt(verticesQuantity)]
            val randomArrivalVertex = newGraph.vertices[Random.nextInt(verticesQuantity)]

            if (randomInitialVertex != randomArrivalVertex) {
                try { newGraph.addEdge(randomInitialVertex, randomArrivalVertex, edgeInitialCost) }
                catch (e : InvalidEdge) {}
            }
        }
        return newGraph
    }

    @Throws(InvalidVertex::class, InvalidEdge::class)
    fun <VertexValueType> createBipartiteGraph(graphName:String,
                                               firstPartitionVerticesQuantity: Int,
                                               secondPartitionVerticesQuantity: Int,
                                               edgesQuantity: Int,
                                               vertexInitialValue: VertexValueType,
                                               edgeInitialCost: Double = 0.0): DefaultGraph<VertexValueType> {
        if (edgesQuantity > (firstPartitionVerticesQuantity * secondPartitionVerticesQuantity))
            throw InvalidEdge("Too many edges")

        if (secondPartitionVerticesQuantity < 0)
            throw InvalidEdge("Too few edges")

        val newGraph = DefaultGraph<VertexValueType>(graphName)

        /* Creates all vertices in graph */
        while (newGraph.vertices.size < firstPartitionVerticesQuantity + secondPartitionVerticesQuantity) {
            newGraph.addVertex(vertexInitialValue)
        }

        /* Creates random edges between vertices */
        while (newGraph.edges.size < edgesQuantity) {
            val randomInitialVertex = newGraph.vertices[Random.nextInt(firstPartitionVerticesQuantity)]
            val randomArrivalVertex = newGraph.vertices[firstPartitionVerticesQuantity + Random.nextInt(firstPartitionVerticesQuantity)]

            try { newGraph.addEdge(randomInitialVertex, randomArrivalVertex, edgeInitialCost) }
            catch (e : InvalidEdge) { continue }
        }
        return newGraph
    }

    @Throws(InvalidVertex::class, InvalidEdge::class)
    fun <VertexValueType> createCompleteGraph(graphName:String,
                                              verticesQuantity: Int,
                                              vertexInitialValue: VertexValueType,
                                              edgeInitialCost: Double = 0.0): DefaultGraph<VertexValueType> {

        val newGraph = DefaultGraph<VertexValueType>(graphName)

        /* Creates all vertices in graph */
        while (newGraph.vertices.size < verticesQuantity) {
            newGraph.addVertex(vertexInitialValue)
        }

        /* Creates edges between all vertices */
        for (initialVertexIndex in newGraph.vertices.indices) {
            val initialVertex = newGraph.vertices[initialVertexIndex]
            for (arrivalVertexIndex in (initialVertexIndex+1 until newGraph.vertices.size)) {
                val arrivalVertex = newGraph.vertices[arrivalVertexIndex]

                try { newGraph.addEdge(initialVertex, arrivalVertex, edgeInitialCost)}
                catch (e: InvalidEdge) { continue }
            }
        }
        return newGraph
    }
}