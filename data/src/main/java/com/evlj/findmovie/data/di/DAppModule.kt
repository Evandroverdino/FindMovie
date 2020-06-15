package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.executors.NetworkExecutor
import com.evlj.findmovie.domain.executors.IExecutor
import io.realm.Realm
import org.koin.dsl.module

val dataAppModule = module {
    single<IExecutor> { NetworkExecutor() }
    single { Realm.getDefaultInstance() }
}