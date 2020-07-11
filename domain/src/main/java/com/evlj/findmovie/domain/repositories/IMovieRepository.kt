package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import kotlinx.coroutines.Deferred

interface IMovieRepository {

    suspend fun getPopularMovies(
        language: String,
        sortBy: String,
        page: Int
    ): Deferred<Discover>

    suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Deferred<MovieDetail>
}