package com.evlj.findmovie.data.repositories

import com.evlj.findmovie.data.mappers.DiscoverMapper
import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import com.evlj.findmovie.domain.repositories.IMovieRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val dispatcher: IDispatcherProvider,
    private val dataRemoteSource: IDataRemoteSource,
    private val discoverMapper: DiscoverMapper,
    private val movieDetailMapper: MovieDetailsMapper
) : IMovieRepository {

    override suspend fun getPopularMovies(
        language: String,
        sortBy: String,
        page: Int
    ): Deferred<Discover> = withContext(dispatcher.background) {
        async {
            dataRemoteSource
                .getPopularMovies(
                    language = language,
                    sortBy = sortBy,
                    page = page
                )
                .await()
                .let(discoverMapper::transform)
        }
    }

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Deferred<MovieDetail> = withContext(dispatcher.background) {
        async {
            dataRemoteSource
                .getMovieDetails(
                    movieId = movieId,
                    language = language
                )
                .await()
                .let(movieDetailMapper::transform)
        }
    }
}