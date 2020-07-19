package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.helpers.SchedulersHelper
import com.evlj.findmovie.data.helpers.extensions.createRoomDb
import com.evlj.findmovie.data.sources.local.DatabaseSource
import com.evlj.findmovie.domain.executors.ISchedulersHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataAppModule = module {
    single<ISchedulersHelper> { SchedulersHelper() }
    single<DatabaseSource> { createRoomDb(androidApplication(), DatabaseSource.DB_NAME) }
}