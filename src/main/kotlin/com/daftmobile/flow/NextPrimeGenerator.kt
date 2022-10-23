package com.daftmobile.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun CoroutineScope.nextPrimeGenerator(
    numbers: ReceiveChannel<BigInteger>,
) = produce(Dispatchers.Default) {
    for (number in numbers) {
        send(number.nextProbablePrime())
    }
}