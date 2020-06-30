package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.sources.local.DatabaseSource
import com.evlj.findmovie.data.sources.remote.DataRemoteSource
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IDataRemoteSource> { DataRemoteSource(get(), get(), get(), get()) }

    single { get<DatabaseSource>().movieLocalSource() }
    single { get<DatabaseSource>().genreLocalSource() }
}