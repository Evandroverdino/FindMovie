package com.evlj.findmovie.di

import com.evlj.findmovie.detail.MovieDetailPresenter
import com.evlj.findmovie.list.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationPresenterModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    factory { MovieDetailPresenter(get(), get(), get(), get()) }
}