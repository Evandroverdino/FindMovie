package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.repositories.DataRepository
import com.evlj.findmovie.domain.repositories.IDataRepository
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val dataRepositoryModule = module {
    single<IDataRepository> { DataRepository(get(), get(), get(), get()) }

    factory { CompositeDisposable() }
}