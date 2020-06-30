package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.MovieDetail
import kotlinx.coroutines.Deferred

interface IDatabaseRepository {

    suspend fun searchMovie(movieId: Int): Deferred<MovieDetail>

    suspend fun saveMovie(movieDetail: MovieDetail): Deferred<Unit>

    suspend fun deleteMovie(movieId: Int): Deferred<Unit>
}