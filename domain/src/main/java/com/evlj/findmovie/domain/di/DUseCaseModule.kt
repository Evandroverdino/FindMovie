package com.evlj.findmovie.domain.di

import com.evlj.findmovie.domain.interactors.DataUseCases
import org.koin.dsl.module

val domainUseCaseModule = module {
    single { DataUseCases(get(), get()) }
}