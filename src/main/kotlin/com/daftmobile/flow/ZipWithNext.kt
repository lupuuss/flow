package com.daftmobile.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("UNCHECKED_CAST")
fun <T> Flow<T>.zipWithNext(): Flow<Pair<T, T>> = flow {
    var previous: Any? = NULL
    collect {
        if (previous !== NULL) emit(previous as T to it)
        previous = it
    }
}
