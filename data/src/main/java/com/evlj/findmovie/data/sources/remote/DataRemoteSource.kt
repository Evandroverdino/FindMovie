package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.MovieApi
import com.evlj.findmovie.data.sources.remote.mappers.DDiscoverMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieDetailMapper
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DataRemoteSource(
    private val movieApi: MovieApi,
    private val dispatcher: IDispatcherProvider,
    private val discoverMapper: DDiscoverMapper,
    private val movieDetailMapper: DMovieDetailMapper
) : IDataRemoteSource {

    override suspend fun getPopularMovies(
        language: String,
        sortBy: String,
        page: Int
    ): Deferred<DDiscover> =
        withContext(dispatcher.background) {
            async {
                movieApi
                    .getPopularMovies(
                        language = language,
                        sortBy = sortBy,
                        page = page
                    )
                    .let(discoverMapper::transform)
            }
        }

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Deferred<DMovieDetail> =
        withContext(dispatcher.background) {
            async {
                movieApi
                    .getMovieDetails(
                        movieId = movieId,
                        language = language
                    )
                    .let(movieDetailMapper::transform)
            }
        }
}