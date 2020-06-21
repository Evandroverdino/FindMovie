package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.MovieDetail
import io.reactivex.Completable
import io.reactivex.Single

interface IDatabaseRepository {

    fun searchMovie(movieId: Int): Single<MovieDetail>

    fun saveMovie(movieDetail: MovieDetail): Completable

    fun deleteMovie(movieId: Int): Completable
}