package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IMovieRepository
import kotlinx.coroutines.Deferred

class MovieUseCases(private val dataRepository: IMovieRepository) {

    suspend fun getPopularMovies(
        language: String,
        sortBy: String,
        page: Int
    ): Deferred<Discover> =
        dataRepository
            .getPopularMovies(
                language = language,
                sortBy = sortBy,
                page = page
            )

    suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Deferred<MovieDetail> =
        dataRepository
            .getMovieDetails(
                movieId = movieId,
                language = language
            )
}