package com.evlj.findmovie.domain.helpers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object SchedulersHelper {

    fun network(): Scheduler {
        return Schedulers.io()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }
}