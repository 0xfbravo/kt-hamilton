package br.ufrrj.graph.core

import br.ufrrj.graph.core.base.Node

class DefaultNode<ValueType>(id: String,
                             value: ValueType): Node<ValueType>(id, value)