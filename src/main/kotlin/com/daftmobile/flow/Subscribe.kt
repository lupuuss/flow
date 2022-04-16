package com.daftmobile.flow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.rxSubscribe(onEach: (T) -> Unit): Job {
    // GlobalScope is just a placeholder to make usage more Rx-like. Don't use GlobalScope!!!
    return onEach(onEach)
        .launchIn(GlobalScope)
}