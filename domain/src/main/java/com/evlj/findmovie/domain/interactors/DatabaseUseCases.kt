package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IDatabaseRepository
import kotlinx.coroutines.Deferred

class DatabaseUseCases(private val dataRepository: IDatabaseRepository) {

    suspend fun searchMovie(movieId: Int): Deferred<MovieDetail> =
        dataRepository.searchMovie(movieId)

    suspend fun saveMovie(movieDetail: MovieDetail): Deferred<Unit> =
        dataRepository.saveMovie(movieDetail)

    suspend fun deleteMovie(movieId: Int): Deferred<Unit> =
        dataRepository.deleteMovie(movieId)
}