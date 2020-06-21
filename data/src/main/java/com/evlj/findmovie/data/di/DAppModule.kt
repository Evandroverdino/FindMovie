package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.executors.ComputationExecutor
import com.evlj.findmovie.data.executors.NetworkExecutor
import com.evlj.findmovie.data.helpers.extensions.createRoomDb
import com.evlj.findmovie.data.sources.local.DatabaseSource
import com.evlj.findmovie.domain.executors.IExecutor
import com.evlj.findmovie.domain.helpers.Constants.COMPUTATION_SCHEDULER
import com.evlj.findmovie.domain.helpers.Constants.NETWORK_SCHEDULER
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataAppModule = module {
    single<IExecutor>(named(NETWORK_SCHEDULER)) { NetworkExecutor() }
    single<IExecutor>(named(COMPUTATION_SCHEDULER)) { ComputationExecutor() }

    single<DatabaseSource> { createRoomDb(androidApplication(), DatabaseSource.DB_NAME) }
}