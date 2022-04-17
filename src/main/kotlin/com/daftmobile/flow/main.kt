@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun createFlow() = flow {
    var i = 0
    log("flow body")
    while (true) emit(i++)
}

fun main() = runBlocking {
    createFlow()
        .flowOn(Dispatchers.Default)
        .sample(10)
        .take(10)
        .collect {
            log("collect >> $it")
        }
    log("runBlocking body")
}

suspend fun log(msg: Any? = null) {
    val context = currentCoroutineContext()
    val msgText = msg?.let { ": $it" }.orEmpty()
    println("${context.job} ${context[CoroutineDispatcher]}$msgText")
}