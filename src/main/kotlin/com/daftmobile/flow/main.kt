package com.daftmobile.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    emit(1)
    delay(500)
    runCatching { emit(2) }.onFailure { println("Caught from emit: $it") }
    delay(500)
    emit(3)
}

fun main() = runBlocking {
    val flow = createFlow().map {
        if (it == 2) throw IllegalStateException("Boom!")
        else "$it"
    }
    try {
        flow.collect {
            println(it)
        }
    } catch (e: Exception) {
        println("Caught: $e")
    }
}