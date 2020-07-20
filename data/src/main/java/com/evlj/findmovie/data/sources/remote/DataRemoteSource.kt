package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.MovieApi
import com.evlj.findmovie.data.sources.remote.mappers.DDiscoverMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieDetailMapper

class DataRemoteSource(
    private val movieApi: MovieApi,
    private val discoverMapper: DDiscoverMapper,
    private val movieDetailMapper: DMovieDetailMapper
) : IDataRemoteSource {

    override suspend fun getPopularMovies(page: Int): DDiscover =
        movieApi
            .getPopularMovies(page = page)
            .let(discoverMapper::transform)

    override suspend fun getMovieDetails(movieId: Int): DMovieDetail =
        movieApi
            .getMovieDetails(movieId = movieId)
            .let(movieDetailMapper::transform)
}