package com.evlj.findmovie.di

import com.evlj.findmovie.detail.MovieDetailViewModel
import com.evlj.findmovie.list.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationViewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { MovieDetailViewModel(get(), get()) }
}