@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class, ExperimentalCoroutinesApi::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime

fun ticker(interval: Long): Flow<Long> = flow {
    var i = 0L
    while(true) {
        emit(i++)
        delay(interval)
    }
}

fun timer(interval: Long): Flow<Long> {
    val startTime = System.currentTimeMillis()
    return flow {
        while (true) {
            emit(System.currentTimeMillis() - startTime)
            delay(interval)
        }
    }
}

fun main() = runBlocking {
    val flow = ticker(200)
        .onEach { println("Emit: $it") }
        .shareIn(this, started = SharingStarted.Eagerly, replay = 0)

    println("Creating flow...")

    delay(300)

    withTimeoutOrNull(300) { flow.simulateSubscriberIn("Alpha", this) }

    delay(300)

    withTimeoutOrNull(300) { flow.simulateSubscriberIn("Beta", this) }

    delay(300)

    cancel()
}

fun <T> SharedFlow<T>.simulateSubscriberIn(tag: String, scope: CoroutineScope): Job {
    return onEach { println("$tag collect $it") }
        .onStart { println("$tag subscription start") }
        .onCompletion { println("$tag subscription end") }
        .launchIn(scope)
}
