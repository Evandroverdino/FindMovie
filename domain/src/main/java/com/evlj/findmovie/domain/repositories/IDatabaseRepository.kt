package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.MovieDetail
import io.reactivex.Completable
import io.reactivex.Single

interface IDatabaseRepository {

    fun searchMovieInDatabase(movieId: Int): Single<MovieDetail>

    fun setMovieAsFavoriteOrNot(movieDetail: MovieDetail): Completable
}