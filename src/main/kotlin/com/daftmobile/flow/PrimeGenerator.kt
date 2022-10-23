package com.daftmobile.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun CoroutineScope.primeGenerator(bits: Int, generators: Int, numbersPerGenerator: Int) = produce(Dispatchers.Default) {
    repeat(generators) {
        launch {
            repeat(numbersPerGenerator) {
                val number: BigInteger
                val time = measureTime {
                    number = BigInteger.probablePrime(bits, Random.asJavaRandom())
                }
                println("PrimeGenerator: time: $time -> $number")
                send(number)
            }
        }
    }
}