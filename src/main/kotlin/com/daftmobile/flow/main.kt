package com.daftmobile.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun createFlow() = flow {
    emit(1)
    delay(500)
    emit(2)
    delay(500)
    emit(3)
}
fun main() =  runBlocking {
    withTimeout(750) {
        createFlow()
            .noisyFilter { it % 2 != 0 }
            .noisyMap { "String($it)" }
            .collect { println("Collect $it") }
    }
}

fun <T, R> Flow<T>.noisyMap(transform: suspend (T) -> R): Flow<R> = map {
    println("Map: $it")
    transform(it)
}

fun <T> Flow<T>.noisyFilter(predicate: (T) -> Boolean): Flow<T> = filter {
    println("Filter: $it")
    predicate(it)
}