@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun createFlow() = flow {
    var i = 0
    while(true) {
        println("Emit $i")
        emit(i++)
    }
}

fun main() = runBlocking {
    val flow1 = createFlow()
        .transform { if (it < 5) emit(it) else throw IllegalStateException("Boom!") }
        .map { "#$it" }
        .catch { throw Exception("Caught exception $it") }

    try {
        println("Collect: ")
        flow1.collect { println(it) }
    } catch (e: Throwable) {
        println("Error: $e")
    }
    println("Done!")

    println("------------------------")

    val flow2 = createFlow().onEach { delay(100) }

    withTimeout(1000) { flow2.collect() }
}