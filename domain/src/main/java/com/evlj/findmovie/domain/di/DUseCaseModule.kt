package com.evlj.findmovie.domain.di

import com.evlj.findmovie.domain.helpers.Constants.COMPUTATION_SCHEDULER
import com.evlj.findmovie.domain.helpers.Constants.NETWORK_SCHEDULER
import com.evlj.findmovie.domain.interactors.DatabaseUseCases
import com.evlj.findmovie.domain.interactors.MovieUseCases
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainUseCaseModule = module {
    single { MovieUseCases(get(named(NETWORK_SCHEDULER)), get()) }
    single { DatabaseUseCases(get(named(COMPUTATION_SCHEDULER)), get()) }
}