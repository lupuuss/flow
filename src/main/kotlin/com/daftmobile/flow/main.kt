@file:OptIn(FlowPreview::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    var i = 0
    while(true) {
        delay(2)
        emit(i++)
    }
}

fun main() = runBlocking {
    val job = createFlow()
        .onEach(::println)
        .launchIn(this)
    // or val job = launch { createFlow().collect(::println) }
    println("Started...")
    delay(30)
    job.cancel()
    println("Stopped")
}