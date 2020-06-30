package com.evlj.findmovie.data.repositories

import com.evlj.findmovie.data.mappers.DiscoverMapper
import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IMovieRepository
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val dataRemoteSource: IDataRemoteSource,
    private val discoverMapper: DiscoverMapper,
    private val movieDetailMapper: MovieDetailsMapper
) : IMovieRepository {

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: Int
    ): Deferred<Discover> = withContext(Dispatchers.IO) {
        async {
            dataRemoteSource
                .getPopularMovies(
                    apiKey = apiKey,
                    language = language,
                    sortBy = sortBy,
                    includeAdult = includeAdult,
                    includeVideo = includeVideo,
                    page = page
                )
                .await()
                .let(discoverMapper::transform)
        }
    }

    override fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<MovieDetail> =
        dataRemoteSource
            .getMovieDetails(
                movieId = movieId,
                apiKey = apiKey,
                language = language
            )
            .map(movieDetailMapper::transform)
}