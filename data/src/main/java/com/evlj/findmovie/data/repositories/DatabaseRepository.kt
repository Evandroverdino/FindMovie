package com.evlj.findmovie.data.repositories

import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.local.IDataLocalSource
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseRepository(
    private val dataLocalSource: IDataLocalSource,
    private val movieDetailMapper: MovieDetailsMapper
) : IDatabaseRepository {

    override fun searchMovieInDatabase(movieId: Int): Single<MovieDetail> =
        dataLocalSource
            .searchMovieInDatabase(movieId)
            .map(movieDetailMapper::transform)

    override fun setMovieAsFavoriteOrNot(movieDetail: MovieDetail): Completable =
        dataLocalSource
            .setMovieAsFavoriteOrNot(movieDetail.let(movieDetailMapper::parseBack))
}