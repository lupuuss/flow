package com.daftmobile.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T, R> Flow<T>.mapIndexed(transform: suspend (index: Int, item: T) -> R): Flow<R> = flow {
    var index = 0
    collect {
        emit(transform(index++, it))
    }
}
