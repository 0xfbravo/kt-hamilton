package br.ufrrj.graph.core.utils

import br.ufrrj.graph.core.DefaultGraph
import br.ufrrj.graph.core.base.MutableGraph
import br.ufrrj.graph.core.exceptions.InvalidEdge
import br.ufrrj.graph.core.exceptions.InvalidVertex
import kotlin.random.Random

object GraphFactory {

    @Throws(InvalidVertex::class, InvalidEdge::class)
    fun <VertexValueType> createSimpleGraph(vertexQuantity: Int,
                                            edgesQuantity: Int,
                                            vertexInitialValue: VertexValueType,
                                            edgeInitialCost: Double = 0.0): MutableGraph<VertexValueType> {
        if (edgesQuantity > (vertexQuantity * (vertexQuantity - 1) / 2))
            throw InvalidEdge("Too many edges")

        if (edgesQuantity < 0)
            throw InvalidEdge("Too few edges")

        val newGraph = DefaultGraph<VertexValueType>()

        /* Creates all vertices in graph */
        while (newGraph.vertices.size < vertexQuantity) {
            newGraph.addVertex(vertexInitialValue)
        }

        /* Creates random edges between vertices */
        while (newGraph.edges.size < edgesQuantity) {
            val randomInitialVertex = newGraph.vertices[Random.nextInt(vertexQuantity)]
            val randomArrivalVertex = newGraph.vertices[Random.nextInt(vertexQuantity)]

            if (randomInitialVertex != randomArrivalVertex) {
                try { newGraph.addEdge(randomInitialVertex, randomArrivalVertex, edgeInitialCost) }
                catch (e : InvalidEdge) {}
            }
        }
        return newGraph
    }

    @Throws(InvalidVertex::class, InvalidEdge::class)
    fun <VertexValueType> createBipartiteGraph(firstPartitionVertexQuantity: Int,
                                               secondPartitionVertexQuantity: Int,
                                               edgesQuantity: Int,
                                               vertexInitialValue: VertexValueType,
                                               edgeInitialCost: Double = 0.0): MutableGraph<VertexValueType> {
        if (edgesQuantity > (firstPartitionVertexQuantity * secondPartitionVertexQuantity))
            throw InvalidEdge("Too many edges")

        if (secondPartitionVertexQuantity < 0)
            throw InvalidEdge("Too few edges")

        val newGraph = DefaultGraph<VertexValueType>()

        /* Creates all vertices in graph */
        while (newGraph.vertices.size < firstPartitionVertexQuantity + secondPartitionVertexQuantity) {
            newGraph.addVertex(vertexInitialValue)
        }

        /* Creates random edges between vertices */
        while (newGraph.edges.size < edgesQuantity) {
            val randomInitialVertex = newGraph.vertices[Random.nextInt(firstPartitionVertexQuantity)]
            val randomArrivalVertex = newGraph.vertices[firstPartitionVertexQuantity + Random.nextInt(firstPartitionVertexQuantity)]

            try { newGraph.addEdge(randomInitialVertex, randomArrivalVertex, edgeInitialCost) }
            catch (e : InvalidEdge) { continue }
        }
        return newGraph
    }

    @Throws(InvalidVertex::class, InvalidEdge::class)
    fun <VertexValueType> createCompleteGraph(vertexQuantity: Int,
                                              vertexInitialValue: VertexValueType,
                                              edgeInitialCost: Double = 0.0): MutableGraph<VertexValueType> {

        val newGraph = DefaultGraph<VertexValueType>()

        /* Creates all vertices in graph */
        while (newGraph.vertices.size < vertexQuantity) {
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