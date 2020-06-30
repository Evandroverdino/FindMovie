package com.evlj.findmovie.domain.di

import com.evlj.findmovie.domain.interactors.DatabaseUseCases
import com.evlj.findmovie.domain.interactors.MovieUseCases
import org.koin.dsl.module

val domainUseCaseModule = module {
    single { MovieUseCases(get()) }
    single { DatabaseUseCases(get()) }
}