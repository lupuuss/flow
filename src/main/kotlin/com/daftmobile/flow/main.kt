@file:OptIn(FlowPreview::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    try {
       for (i in 3..7) {
           println("Emitting: $i")
           emit(i)
       }
    } finally {
        println("I am out!")
    }
}

fun main() = runBlocking {

    log("collect") {
        createFlow().collect { println("Collecting: $it") }
    }

    log("collectIndexed") {
        createFlow().collectIndexed { index, value -> println("Collecting: Index=$index Value=$value") }
    }

    log("first") {
        createFlow().first()
    }

    log("lastOrNull") {
        createFlow().lastOrNull()
    }

    log("single") {
        createFlow().single()
    }

    log("reduce(Int::times)") {
        createFlow().reduce(Int::times) // or .reduce { acc, value -> acc * value }
    }

    log("fold(0, Int::plus)") {
        createFlow().fold(0, Int::plus)
    }

    log("runningReduce(Int::times)") {
        createFlow()
            .runningReduce(Int::times)
            .collect { println("Running reduce collect: $it") }
    }

    log("scan/runningFold(0, Int::plus)") {
        createFlow()
            .scan(0, Int::plus) // alias for .runningFold(0, Int::plus)
            .collect { println("Scan collect: $it") }
    }

    log("count { it % 2 == 0 }") {
        createFlow().count { it % 2 == 0 }
    }

    log("toList") {
        createFlow().toList()
    }
}

inline fun <T> log(tag: String, block: () -> T) {
    println("--- $tag ---")
    runCatching { block() }
        .onSuccess { println("--- $tag ==> $it ---") }
        .onFailure { println("--- $tag ==> $it ---") }
    println()
}