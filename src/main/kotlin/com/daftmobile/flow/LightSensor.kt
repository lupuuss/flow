package com.daftmobile.flow

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(DelicateCoroutinesApi::class)
object LightSensor {

    private val listeners = mutableListOf<OnLightChangeListener>()
    private val job = GlobalScope.launch {
        try {
            while (true) {
                delay(500)
                val value = Random.nextDouble()
                if (value > 0.8) {
                    listeners.forEach { it.onError(Exception("Fail!")) }
                    break
                }
                listeners.forEach { it.onLightChange(value) }
            }
        } finally {
            listeners.forEach { it.onCompleted() }
        }
    }

    fun addOnLightChangeListener(listener: OnLightChangeListener) {
        listeners.add(listener)
    }

    fun removeOnLightChangeListener(listener: OnLightChangeListener) {
        listeners.remove(listener)
    }

    fun close() {
        job.cancel()
    }

    interface OnLightChangeListener {
        fun onLightChange(value: Double)

        fun onError(cause: Throwable)

        fun onCompleted()
    }
}