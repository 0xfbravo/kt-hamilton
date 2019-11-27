package br.ufrrj.graph.core.utils

inline fun <T> measure(loggingFunction: (Long, Long) -> Unit = { time, usedMemory -> println("Execution Time: ${time}ms | Memory Usage: ${usedMemory}MB") },
                       function: () -> T): T {

    System.gc()
    val rt = Runtime.getRuntime()
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    loggingFunction.invoke(System.currentTimeMillis() - startTime, (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024)
    return result
}