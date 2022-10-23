@file:Suppress("unused")
@file:OptIn(ExperimentalTime::class)

package com.daftmobile.flow

import kotlinx.coroutines.runBlocking
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun main() {
    val time = measureTime {
        runBlocking {
            val numbers = primeGenerator(2048, 5, 2)
            repeat(4) { nextPrimeGenerator(numbers, tag = "#$it") }
        }
    }
    println("Time: $time")
}

fun randomBigInteger(bits: Int): BigInteger = generateSequence { BigInteger(bits, Random.asJavaRandom()) }
    .filter { it > BigInteger.ONE }
    .first()