package com.daftmobile.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    emit(1)
    delay(500)
    emit(2)
    delay(500)
    throw IllegalStateException("Boom!")
}
fun main() = runBlocking {
    val flow = createFlow()
    try {
        flow.collect {
            println(it)
        }
    } catch (e: Exception) {
        println("Caught: $e")
    }
}