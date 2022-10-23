package com.daftmobile.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun CoroutineScope.primeGenerator(numbers: SendChannel<BigInteger>, bits: Int, count: Int) = launch(Dispatchers.Default) {
    repeat(count) {
        val number: BigInteger
        val time = measureTime {
            number = BigInteger.probablePrime(bits, Random.asJavaRandom())
        }
        println("PrimeGenerator: time: $time -> $number")
        numbers.send(number)
    }
}