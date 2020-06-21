package com.evlj.findmovie.data.executors

import com.evlj.findmovie.domain.executors.IExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ComputationExecutor : IExecutor {

    override val scheduler: Scheduler
        get() = Schedulers.computation()
}