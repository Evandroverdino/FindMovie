package com.evlj.findmovie.data.sources.local

import com.evlj.findmovie.data.entities.DMovieDetail
import io.reactivex.Completable
import io.reactivex.Single

interface IDataLocalSource {

    fun searchMovieInDatabase(movieId: Int): Single<DMovieDetail>

    fun setMovieAsFavoriteOrNot(movieDetail: DMovieDetail): Completable
}