@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class, ExperimentalCoroutinesApi::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.time.ExperimentalTime

fun main() = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.SUSPEND
    ) // Change parameters to achieve different behaviours!

    sharedFlow.subscriptionCount
        .onEach { println("Subscribers: $it") }
        .launchIn(this)

    sharedFlow.simulateSubscriberIn("Alpha", this)
    sharedFlow.simulateSubscriberIn("Beta ", this)

    delay(100)

    println("Start emission!")

    for (i in 1..3) {
        println("# emit start  $i")
        sharedFlow.emit(i)
        println("# emit done   $i")
    }

    delay(500)

    sharedFlow.simulateSubscriberIn("Gamma", this)

    delay(500)

    sharedFlow.resetReplayCache()
    sharedFlow.simulateSubscriberIn("Delta", this)

    delay(500)
    cancel()
}

fun <T> SharedFlow<T>.simulateSubscriberIn(tag: String, scope: CoroutineScope): Job {
    return onEach {
        println("$tag collect start  $it")
        delay(100)
        println("$tag collect done   $it")
    }.launchIn(scope)
}
