package com.evlj.findmovie.di

import com.evlj.findmovie.detail.MovieDetailPresenter
import com.evlj.findmovie.list.MainPresenter
import org.koin.dsl.module

val presentationPresenterModule = module {
    factory { MainPresenter(get(), get(), get()) }
    factory { MovieDetailPresenter(get(), get(), get(), get()) }
}