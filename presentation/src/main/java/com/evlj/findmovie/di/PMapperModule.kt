package com.evlj.findmovie.di

import com.evlj.findmovie.mappers.PDiscoverMapper
import com.evlj.findmovie.mappers.PGenreMapper
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.mappers.PMovieMapper
import org.koin.dsl.module

val presentationMapperModule = module {
    factory { PDiscoverMapper(get()) }
    factory { PGenreMapper() }
    factory { PMovieDetailMapper(get()) }
    factory { PMovieMapper() }
}