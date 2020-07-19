package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import kotlinx.coroutines.Deferred

interface IMovieRepository {

    suspend fun getPopularMovies(page: Int): Deferred<Discover>

    suspend fun getMovieDetails(movieId: Int): Deferred<MovieDetail>

    suspend fun saveMovie(movieDetail: MovieDetail): Deferred<Unit>

    suspend fun deleteMovie(movieId: Int): Deferred<Unit>
}