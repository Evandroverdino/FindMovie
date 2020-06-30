package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.repositories.DatabaseRepository
import com.evlj.findmovie.data.repositories.MovieRepository
import com.evlj.findmovie.domain.repositories.IDatabaseRepository
import com.evlj.findmovie.domain.repositories.IMovieRepository
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val dataRepositoryModule = module {
    single<IMovieRepository> { MovieRepository(get(), get(), get(), get()) }
    single<IDatabaseRepository> { DatabaseRepository(get(), get(), get(), get()) }

    factory { CompositeDisposable() }
}