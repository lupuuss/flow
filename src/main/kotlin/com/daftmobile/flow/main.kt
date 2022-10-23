@file:Suppress("unused")

package com.daftmobile.flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<String>()
    launch { channel.send("Hello channel!") }
    println(channel.receive())
}