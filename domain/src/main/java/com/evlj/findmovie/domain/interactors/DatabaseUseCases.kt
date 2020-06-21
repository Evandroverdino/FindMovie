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

    fun searchMovieInDatabase(movieId: Int): Single<MovieDetail> =
        dataRepository
            .singleOnExecutor { searchMovieInDatabase(movieId) }

    fun setMovieAsFavoriteOrNot(movieDetail: MovieDetail): Completable =
        dataRepository
            .completableOnExecutor { setMovieAsFavoriteOrNot(movieDetail) }
}