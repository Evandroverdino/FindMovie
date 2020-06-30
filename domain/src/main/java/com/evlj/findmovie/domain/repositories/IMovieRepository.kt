package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import io.reactivex.Single
import kotlinx.coroutines.Deferred

interface IMovieRepository {

    suspend fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Deferred<Discover>

    fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<MovieDetail>
}