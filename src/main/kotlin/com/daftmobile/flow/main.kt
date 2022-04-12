@file:OptIn(FlowPreview::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    var i = 0
    while (true) emit(i++)
}

fun flow1() = createFlow()
    .drop(1)
    .take(10)
    .filterNot { it % 2 == 0 }
    .map { "+".repeat(it) }

fun flow2() = emptyFlow<Int>()
    .onEmpty { emit(1) }
    .onStart { emit(2) }
    .onEmpty { emit(7) }
    .onCompletion { emit(3) }

fun flow3() = createFlow()
    .drop(1)
    .takeWhile { it % 100 != 0 }
    .map { it.coerceAtMost(3) }
    .distinctUntilChanged()

fun flow4() = createFlow()
    .sample(1_000)
    .transform { if (it % 3 == 0) error("Boom!") else emit(it) }
    .retry(retries = 2) { it is IllegalStateException }

fun main() = runBlocking {
    flow1().collect { println(it) }
}