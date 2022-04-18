@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class, ExperimentalCoroutinesApi::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime

fun main() = runBlocking {
    val flow = MutableStateFlow(1)

    flow.value = 2

    delay(300)

    launchDelayed(100) { flow.value = 3 }
    launchDelayed(150) { flow.value = 3 }
    launchDelayed(200) { flow.value = 4 }

    withTimeoutOrNull(300) { flow.simulateSubscriberIn("Alpha", this) }

    withTimeoutOrNull(300) { flow.simulateSubscriberIn("Beta", this) }

    cancel()
}

fun <T> SharedFlow<T>.simulateSubscriberIn(tag: String, scope: CoroutineScope): Job {
    return onEach { println("$tag collect $it") }
        .onStart { println("$tag subscription start") }
        .onCompletion { println("$tag subscription end") }
        .launchIn(scope)
}

fun CoroutineScope.launchDelayed(delayMs: Long, block: suspend () -> Unit) = launch {
    delay(delayMs)
    block()
}