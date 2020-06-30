package com.evlj.findmovie.data.executors

import com.evlj.findmovie.domain.executors.IDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProvider : IDispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val background: CoroutineDispatcher
        get() = Dispatchers.IO
}