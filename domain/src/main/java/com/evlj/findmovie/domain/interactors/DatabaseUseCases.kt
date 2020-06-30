package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.executors.IExecutor
import com.evlj.findmovie.domain.interactors.base.BaseUseCase
import com.evlj.findmovie.domain.repositories.IDatabaseRepository
import kotlinx.coroutines.Deferred

class DatabaseUseCases(
    executor: IExecutor,
    private val dataRepository: IDatabaseRepository
) : BaseUseCase(executor) {

    suspend fun searchMovie(movieId: Int): Deferred<MovieDetail> =
        dataRepository.searchMovie(movieId)

    suspend fun saveMovie(movieDetail: MovieDetail): Deferred<Unit> =
        dataRepository.saveMovie(movieDetail)

    suspend fun deleteMovie(movieId: Int): Deferred<Unit> =
        dataRepository.deleteMovie(movieId)
}