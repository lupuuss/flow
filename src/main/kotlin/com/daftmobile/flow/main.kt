@file:OptIn(FlowPreview::class)
@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun createFlow() = flow {
    var i = 0
    while(true) {
        delay(2)
        emit(i++)
    }
}

fun main() = runBlocking {
    val disposable = createFlow().rxSubscribe { println(it) } // How to achieve Rx-like subscribe?
    println("Started...")
    delay(20)
    disposable.cancel()
    println("Stopped")
}