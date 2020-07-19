package com.evlj.findmovie.domain.interactors

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IMovieRepository
import kotlinx.coroutines.Deferred

class MovieUseCases(private val dataRepository: IMovieRepository) {

    suspend fun getPopularMovies(page: Int): Deferred<Discover> =
        dataRepository.getPopularMovies(page)

    suspend fun getMovieDetails(movieId: Int): Deferred<MovieDetail> =
        dataRepository.getMovieDetails(movieId)

    suspend fun saveMovie(movieDetail: MovieDetail): Deferred<Unit> =
        dataRepository.saveMovie(movieDetail)

    suspend fun deleteMovie(movieId: Int): Deferred<Unit> =
        dataRepository.deleteMovie(movieId)
}