package com.daftmobile.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    emit(1)
    delay(500)
    emit(2)
    delay(500)
    emit(3)
}
fun main() =  runBlocking {
    createFlow()
        .collect { println(it) }
}
