@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    LightSensor
        .consumeAsFlow()
        .take(10)
        .collect { println(it) }
}