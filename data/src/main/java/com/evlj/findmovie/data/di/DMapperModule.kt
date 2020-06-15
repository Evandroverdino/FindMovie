package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.mappers.DiscoverMapper
import com.evlj.findmovie.data.mappers.GenreMapper
import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.mappers.MovieMapper
import com.evlj.findmovie.data.sources.remote.mappers.DDiscoverMapper
import com.evlj.findmovie.data.sources.remote.mappers.DGenreMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieDetailMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieMapper
import org.koin.dsl.module

val dataMapperModule = module {
    single { DDiscoverMapper(get()) }
    single { DGenreMapper() }
    single { DMovieDetailMapper(get()) }
    single { DMovieMapper() }

    single { DiscoverMapper(get()) }
    single { GenreMapper() }
    single { MovieDetailsMapper(get()) }
    single { MovieMapper() }
}