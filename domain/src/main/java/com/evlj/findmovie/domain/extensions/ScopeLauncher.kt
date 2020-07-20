package com.evlj.findmovie.domain.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> CoroutineScope.launchJob(
    requestBlock: suspend () -> T,
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit
) = launch {
    try {
        onSuccess(requestBlock())
    } catch (error: Throwable) {
        onFailure(error)
    }
}