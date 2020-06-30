package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import kotlinx.coroutines.Deferred

interface IMovieRepository {

    suspend fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Deferred<Discover>

    suspend fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Deferred<MovieDetail>
}