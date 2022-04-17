@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun createFlow() = flow {
    for (i in 1..10) {
        log("Emit: $i")
        emit(i)
        delay(100)
    }
}

fun main() = runBlocking {
    val time = measureTime {
        createFlow()
            .collectLatest {
                log("Collecting: $it")
                delay(300)
                log("Collected: $it")
            }
    }
    println("Time: $time")
}

suspend fun log(msg: Any? = null) {
    val context = currentCoroutineContext()
    val msgText = msg?.let { ": $it" }.orEmpty()
    println("${context.job} ${context[CoroutineDispatcher]}$msgText")
}