package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.repositories.MovieRepository
import com.evlj.findmovie.domain.repositories.IMovieRepository
import org.koin.dsl.module

val dataRepositoryModule = module {
    single<IMovieRepository> { MovieRepository(get(), get(), get(), get(), get()) }
}