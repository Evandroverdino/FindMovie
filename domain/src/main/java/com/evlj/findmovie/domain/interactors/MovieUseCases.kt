package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.Movie
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.entities.paginated.PaginationCommand
import com.evlj.findmovie.domain.executors.ISchedulersHelper
import com.evlj.findmovie.domain.interactors.base.BaseUseCase
import com.evlj.findmovie.domain.repositories.IMovieRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MovieUseCases(
    private val schedulerHelper: ISchedulersHelper,
    private val dataRepository: IMovieRepository
) : BaseUseCase() {

    fun getPopularMovies(command: Observable<PaginationCommand>): Observable<List<Movie>> =
        dataRepository
            .observableOnExecutor(schedulerHelper.io) { getPopularMovies(command) }

    fun getMovieDetails(movieId: Int): Single<MovieDetail> =
        dataRepository
            .singleOnExecutor(schedulerHelper.io) { getMovieDetails(movieId) }

    fun saveMovie(movieDetail: MovieDetail): Completable =
        dataRepository
            .completableOnExecutor(schedulerHelper.computation) { saveMovie(movieDetail) }

    fun deleteMovie(movieId: Int): Completable =
        dataRepository
            .completableOnExecutor(schedulerHelper.computation) { deleteMovie(movieId) }
}