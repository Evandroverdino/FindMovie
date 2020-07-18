package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.helpers.SchedulersHelper
import com.evlj.findmovie.domain.interactors.base.BaseUseCase
import com.evlj.findmovie.domain.repositories.IMovieRepository
import io.reactivex.Completable
import io.reactivex.Single

class MovieUseCases(private val dataRepository: IMovieRepository) : BaseUseCase() {

    fun getPopularMovies(page: Int): Single<Discover> =
        dataRepository
            .singleOnExecutor(SchedulersHelper.network()) { getPopularMovies(page) }

    fun getMovieDetails(movieId: Int): Single<MovieDetail> =
        dataRepository
            .singleOnExecutor(SchedulersHelper.network()) { getMovieDetails(movieId) }

    fun saveMovie(movieDetail: MovieDetail): Completable =
        dataRepository
            .completableOnExecutor(SchedulersHelper.computation()) { saveMovie(movieDetail) }

    fun deleteMovie(movieId: Int): Completable =
        dataRepository
            .completableOnExecutor(SchedulersHelper.computation()) { deleteMovie(movieId) }
}