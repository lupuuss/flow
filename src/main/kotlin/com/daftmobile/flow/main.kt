@file:OptIn(FlowPreview::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun createFlow() = flowOf(1, 2, 3, 4, 5)

fun main() = runBlocking {
    createFlow()
        .mapIndexed { index, item -> index + item }
        .zipWithNext()
        .onEach(::println)
        .all { (a, b) -> a < b }
        .let(::println)

    println("Sum: ${createFlow().sum()}")
    println("Average: ${createFlow().average()}")
}
