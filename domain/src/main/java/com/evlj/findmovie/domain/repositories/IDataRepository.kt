package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import io.reactivex.Completable
import io.reactivex.Single

interface IDataRepository {

    fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Single<Discover>

    fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<MovieDetail>

    fun searchMovieInDatabase(movieId: Int): Single<MovieDetail>

    fun setMovieAsFavoriteOrNot(movieDetail: MovieDetail): Completable
}