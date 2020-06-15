package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.sources.local.DataLocalSource
import com.evlj.findmovie.data.sources.local.IDataLocalSource
import com.evlj.findmovie.data.sources.remote.DataRemoteSource
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IDataRemoteSource> { DataRemoteSource(get(), get(), get()) }
    single<IDataLocalSource> { DataLocalSource(get()) }
}