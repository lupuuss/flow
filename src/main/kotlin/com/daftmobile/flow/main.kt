@file:OptIn(FlowPreview::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime

fun divisorsOf(number: Int): Flow<Int> = flow {
    log("Divisors of $number flow!")
    emit(1)
    for (i in 2..(number / 2)) if (number % i == 0) emit(i)
    if (number != 1) emit(number)
}

fun main() = runBlocking {
    val divisors = List(10) { divisorsOf(it + 1) }
    combine(flows = divisors, transform = Array<*>::contentToString)
        .flowOn(Dispatchers.Default)
        .collect(::println)
}

suspend fun log(msg: Any? = null) {
    val context = currentCoroutineContext()
    val msgText = msg?.let { ": $it" }.orEmpty()
    println("${context.job} ${context[CoroutineDispatcher]}$msgText")
}