package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.executors.IExecutor
import com.evlj.findmovie.domain.interactors.base.BaseUseCase
import com.evlj.findmovie.domain.repositories.IDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseUseCases(
    executor: IExecutor,
    private val dataRepository: IDatabaseRepository
) : BaseUseCase(executor) {

    fun searchMovie(movieId: Int): Single<MovieDetail> =
        dataRepository
            .singleOnExecutor { searchMovie(movieId) }

    fun saveMovie(movieDetail: MovieDetail): Completable =
        dataRepository
            .completableOnExecutor { saveMovie(movieDetail) }

    fun deleteMovie(movieId: Int): Completable =
        dataRepository
            .completableOnExecutor { deleteMovie(movieId) }
}