package com.evlj.findmovie.di

import com.evlj.findmovie.detail.MovieDetailContract
import com.evlj.findmovie.detail.MovieDetailPresenter
import com.evlj.findmovie.list.MainContract
import com.evlj.findmovie.list.MainPresenter
import org.koin.dsl.module

val presentationPresenterModule = module {
    factory<MainContract.Presenter> { MainPresenter(get(), get()) }
    factory<MovieDetailContract.Presenter> { MovieDetailPresenter(get(), get()) }
}