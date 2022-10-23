@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun createFlow() = flow {
    withContext(Dispatchers.Default) {
        var i = 0
        while (true) {
            delay(500)
            emit(i++)
        }
    }
}

fun main() = runBlocking {
    createFlow()
        .take(6)
        .collect { println(it) }
}