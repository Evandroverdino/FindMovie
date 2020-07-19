package com.evlj.findmovie.data.helpers

import com.evlj.findmovie.domain.executors.ISchedulersHelper
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulersHelper : ISchedulersHelper {

    override val io: Scheduler = Schedulers.io()
    override val computation: Scheduler = Schedulers.computation()
}