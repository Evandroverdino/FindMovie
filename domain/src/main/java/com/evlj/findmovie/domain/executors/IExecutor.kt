package com.evlj.findmovie.domain.executors

import io.reactivex.Scheduler

interface IExecutor {
    val scheduler: Scheduler
}