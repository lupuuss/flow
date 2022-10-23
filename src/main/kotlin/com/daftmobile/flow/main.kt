@file:Suppress("unused")
@file:OptIn(ExperimentalTime::class)

package com.daftmobile.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun main() {
    val time = measureTime {
        runBlocking {
            val numbers = List(5) { primeGenerator(2048, 2) }.mergeIn(this)
            List(5) { nextPrimeGenerator(numbers) }
                .mergeIn(this)
                .consumeEach(::println)
        }
    }
    println("Time: $time")
}

fun <T> List<ReceiveChannel<T>>.mergeIn(scope: CoroutineScope): ReceiveChannel<T> = scope.produce {
    for (channel in this@mergeIn) {
        launch {
            for (element in channel) {
                send(element)
            }
        }
    }
}