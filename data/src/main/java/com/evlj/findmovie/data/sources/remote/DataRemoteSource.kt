package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.MovieApi
import com.evlj.findmovie.data.sources.remote.mappers.DDiscoverMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieDetailMapper
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DataRemoteSource(
    private val movieApi: MovieApi,
    private val discoverMapper: DDiscoverMapper,
    private val movieDetailMapper: DMovieDetailMapper
) : IDataRemoteSource {

    override suspend fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Deferred<DDiscover> = withContext(Dispatchers.IO) {
        async {
            movieApi
                .getPopularMovies(
                    apiKey = apiKey,
                    language = language,
                    sortBy = sortBy,
                    includeAdult = includeAdult,
                    includeVideo = includeVideo,
                    page = page
                )
                .let(discoverMapper::transform)
        }
    }

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