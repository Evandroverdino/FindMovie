package com.evlj.findmovie.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val presentationAppModule = module {
    factory { CompositeDisposable() }
}