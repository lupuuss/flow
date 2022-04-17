@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun createFlow() = flow {
    for (i in 1..10) {
        emit(i)
        log("Emit: $i")
        delay(100)
    }
}

fun main() = runBlocking {
    val time = measureTime {
        createFlow()
            .collect {
                log("Collect: $it")
                delay(300)
            }
    }
    println("Time: $time")
}

suspend fun log(msg: Any? = null) {
    val context = currentCoroutineContext()
    val msgText = msg?.let { ": $it" }.orEmpty()
    println("${context.job} ${context[CoroutineDispatcher]}$msgText")
}