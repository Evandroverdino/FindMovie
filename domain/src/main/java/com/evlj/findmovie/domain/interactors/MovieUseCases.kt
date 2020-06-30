package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IMovieRepository
import kotlinx.coroutines.Deferred

class MovieUseCases(private val dataRepository: IMovieRepository) {

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

    suspend fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Deferred<MovieDetail> =
        dataRepository
            .getMovieDetails(
                movieId = movieId,
                apiKey = apiKey,
                language = language
            )
}