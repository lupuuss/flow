package com.daftmobile.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun CoroutineScope.nextPrimeGenerator(
    numbers: ReceiveChannel<BigInteger>,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) = launch(dispatcher) {
    for (number in numbers) { // or numbers.consumeEach
        val nextPrime: BigInteger
        val time = measureTime {
            nextPrime = number.nextProbablePrime()
        }
        println("NextPrimeGenerator: time: $time -> $nextPrime")
    }
}