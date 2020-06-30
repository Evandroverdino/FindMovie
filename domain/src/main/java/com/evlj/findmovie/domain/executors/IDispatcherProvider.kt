package com.evlj.findmovie.domain.executors

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {

    val main: CoroutineDispatcher
    val background: CoroutineDispatcher
}