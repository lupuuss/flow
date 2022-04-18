@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime

fun divisorsOf(number: Int): Flow<Int> = flow {
    emit(1)
    for (i in 2..(number / 2)) if (number % i == 0) emit(i)
    if (number != 1) emit(number)
}

fun main() = runBlocking {
    val div8 = divisorsOf(8)
    val div12 = divisorsOf(12)

    println("Left:  " + div8.toList().prettyToString())
    println("Right: " + div12.toList().prettyToString())

    div8.zip(div12) { left, right -> left to right }
        .collect(::println)
}

fun List<*>.prettyToString() = joinToString(separator = " | ") { it.toString().padStart(2, ' ') }