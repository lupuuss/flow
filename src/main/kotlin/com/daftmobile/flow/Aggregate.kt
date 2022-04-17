package com.daftmobile.flow

import kotlinx.coroutines.flow.*

suspend fun Flow<Int>.sum(): Int = fold(0, Int::plus)

suspend fun <T> Flow<T>.all(predicate: (T) -> Boolean) = transformWhile { item -> predicate(item).also { emit(it) } }.last()

fun Flow<Int>.runningAverage(): Flow<Double> = flow {
    var count = 0
    var sum = 0
    collect {
        count++
        sum += it
        emit(sum.toDouble() / count.toDouble())
    }
}

suspend fun Flow<Int>.average(): Double = runningAverage().lastOrNull() ?: Double.NaN
