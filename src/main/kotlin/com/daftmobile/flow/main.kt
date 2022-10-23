@file:Suppress("unused")
@file:OptIn(ExperimentalTime::class)

package com.daftmobile.flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun main() {
    val time = measureTime {
        runBlocking {
            val numbers = Channel<BigInteger>()
            repeat(4) { nextPrimeGenerator(numbers, tag = "#$it") }
            for (i in 1..10) numbers.send(randomBigInteger(2048))
            numbers.close()
        }
    }
    println("Time: $time")
}

fun randomBigInteger(bits: Int): BigInteger = generateSequence { BigInteger(bits, Random.asJavaRandom()) }
    .filter { it > BigInteger.ONE }
    .first()