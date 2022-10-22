package com.daftmobile.flow

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun LightSensor.consumeAsFlow() = callbackFlow {
    val listener = object : LightSensor.OnLightChangeListener {
        override fun onLightChange(value: Double) {
            trySend(value)
        }

        override fun onError(cause: Throwable) {
            close(cause = cause)
        }

        override fun onCompleted() {
            close()
        }

    }
    addOnLightChangeListener(listener)
    awaitClose { removeOnLightChangeListener(listener) }
}