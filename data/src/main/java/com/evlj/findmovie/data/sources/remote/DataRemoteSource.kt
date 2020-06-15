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

    override fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Single<DDiscover> =
        movieApi
            .getPopularMovies(
                apiKey = apiKey,
                language = language,
                sortBy = sortBy,
                includeAdult = includeAdult,
                includeVideo = includeVideo,
                page = page
            )
            .map(discoverMapper::transform)

    override fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<DMovieDetail> =
        movieApi
            .getMovieDetails(
                movieId = movieId,
                apiKey = apiKey,
                language = language
            )
            .map(movieDetailMapper::transform)
}