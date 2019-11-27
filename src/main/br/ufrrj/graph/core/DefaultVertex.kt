package br.ufrrj.graph.core

import br.ufrrj.graph.core.base.Vertex

class DefaultVertex<ValueType>(id: String,
                               value: ValueType): Vertex<ValueType>(id, value)