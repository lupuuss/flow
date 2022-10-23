package com.daftmobile.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.produce
import kotlin.random.Random
import kotlin.random.asJavaRandom
import java.math.BigInteger


fun CoroutineScope.primeGenerator(bits: Int, count: Int) = produce(Dispatchers.Default) {
    repeat(count) {
        send(BigInteger.probablePrime(bits, Random.asJavaRandom()))
    }
}