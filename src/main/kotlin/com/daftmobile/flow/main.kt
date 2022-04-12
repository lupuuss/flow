package com.daftmobile.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
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
    createFlow()
        .catch {
            emit(3)                        // We can emit fallback/default here...
            println("Logged exception: $it")     // ..or log it
            throw Exception("Rethrowing...", it) // ...or rethrow it!
        }
        .collect {
            println(it)
        }
}