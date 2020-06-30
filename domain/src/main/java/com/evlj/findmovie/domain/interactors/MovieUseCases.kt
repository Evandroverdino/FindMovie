package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.executors.IExecutor
import com.evlj.findmovie.domain.interactors.base.BaseUseCase
import com.evlj.findmovie.domain.repositories.IMovieRepository
import io.reactivex.Single
import kotlinx.coroutines.Deferred

class MovieUseCases(
    executor: IExecutor,
    private val dataRepository: IMovieRepository
) : BaseUseCase(executor) {

    suspend fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Deferred<Discover> =
        dataRepository
            .getPopularMovies(
                apiKey = apiKey,
                language = language,
                sortBy = sortBy,
                includeAdult = includeAdult,
                includeVideo = includeVideo,
                page = page
            )

    fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<MovieDetail> =
        dataRepository
            .singleOnExecutor {
                getMovieDetails(
                    movieId = movieId,
                    apiKey = apiKey,
                    language = language
                )
            }
}