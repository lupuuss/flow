package com.daftmobile.flow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun createFlow() = flowOf(1, 2, 3, 4) // or listOf(1, 2, 3, 4).asFlow()

fun main() =  runBlocking {
    createFlow().collect {
        if (it == 2) cancel()
        println(it)
    }
}