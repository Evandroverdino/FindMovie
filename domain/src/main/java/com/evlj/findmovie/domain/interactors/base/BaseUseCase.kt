package com.evlj.findmovie.domain.interactors.base

import com.evlj.findmovie.domain.executors.IExecutor
import io.reactivex.Completable
import io.reactivex.Single

abstract class BaseUseCase(private val executor: IExecutor) {

    fun <T, R> T.singleOnExecutor(block: T.() -> Single<R>): Single<R> =
        block().subscribeOn(executor.scheduler)

    fun <T> T.completableOnExecutor(block: T.() -> Completable): Completable =
        block().subscribeOn(executor.scheduler)
}