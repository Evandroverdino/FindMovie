package com.evlj.findmovie.domain.interactors.base

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single

abstract class BaseUseCase {

    fun <T, R> T.singleOnExecutor(scheduler: Scheduler, block: T.() -> Single<R>): Single<R> =
        block().subscribeOn(scheduler)

    fun <T> T.completableOnExecutor(scheduler: Scheduler, block: T.() -> Completable): Completable =
        block().subscribeOn(scheduler)
}