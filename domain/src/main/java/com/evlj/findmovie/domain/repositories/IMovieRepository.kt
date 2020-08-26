package com.evlj.findmovie.domain.repositories

import com.evlj.findmovie.domain.entities.Movie
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.entities.paginated.PaginationCommand
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IMovieRepository {

    fun getPopularMovies(command: Observable<PaginationCommand>): Observable<List<Movie>>

    fun getMovieDetails(movieId: Int): Single<MovieDetail>

    fun saveMovie(movieDetail: MovieDetail): Completable

    fun deleteMovie(movieId: Int): Completable
}