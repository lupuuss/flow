@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun createFlow() = flow { for (i in 1..10) emit(i) }

fun divisorsOf(number: Int): Flow<Int> = flow {
    log("Divisors of $number:")
    emit(1)
    for (i in 2..(number / 2)) {
        if (number % i == 0) {
            delay(20)
            emit(i)
        }
    }
    if (number != 1) emit(number)
}

fun main() = runBlocking {
    val time = measureTime {
        createFlow()
            .map(::divisorsOf)   // or just .flatMapConcat(::divisorsOf)
            .flattenConcat()
            .collect(::println)
    }
    println("Time: $time")
}

suspend fun log(msg: Any? = null) {
    val context = currentCoroutineContext()
    val msgText = msg?.let { ": $it" }.orEmpty()
    println("${context.job} ${context[CoroutineDispatcher]}$msgText")
}