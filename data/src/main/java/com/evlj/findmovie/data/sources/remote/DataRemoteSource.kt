package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.MovieApi
import com.evlj.findmovie.data.sources.remote.mappers.DDiscoverMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieDetailMapper
import io.reactivex.Single

class DataRemoteSource(
    private val movieApi: MovieApi,
    private val discoverMapper: DDiscoverMapper,
    private val movieDetailMapper: DMovieDetailMapper
) : IDataRemoteSource {

    override fun getPopularMovies(page: Int): Single<DDiscover> =
        movieApi
            .getPopularMovies(page = page)
            .map(discoverMapper::transform)

    override fun getMovieDetails(movieId: Int): Single<DMovieDetail> =
        movieApi
            .getMovieDetails(movieId = movieId)
            .map(movieDetailMapper::transform)
}