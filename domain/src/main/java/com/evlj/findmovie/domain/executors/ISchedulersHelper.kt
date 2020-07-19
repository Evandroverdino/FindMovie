package com.evlj.findmovie.domain.executors

import io.reactivex.Scheduler

interface ISchedulersHelper {

    val io: Scheduler
    val computation: Scheduler

}