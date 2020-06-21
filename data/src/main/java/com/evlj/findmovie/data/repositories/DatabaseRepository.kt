package com.evlj.findmovie.data.repositories

import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.local.dao.IGenreLocalSource
import com.evlj.findmovie.data.sources.local.dao.IMovieLocalSource
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseRepository(
    private val movieLocalSource: IMovieLocalSource,
    private val genreLocalSource: IGenreLocalSource,
    private val movieDetailMapper: MovieDetailsMapper
) : IDatabaseRepository {

    override fun searchMovie(movieId: Int): Single<MovieDetail> =
        genreLocalSource
            .searchGenres(movieId)
            .flatMap { genres ->
                movieLocalSource
                    .searchMovie(movieId)
                    .map { it.copy(_genres = genres) }
            }
            .map(movieDetailMapper::transform)

    override fun saveMovie(movieDetail: MovieDetail): Completable =
        Completable.fromAction {
            movieLocalSource
                .transaction {
                    movieDetail.let(movieDetailMapper::parseBack).let {
                        insert(it)
                        it.genres.forEach(genreLocalSource::insert)
                    }
                }
        }

    override fun deleteMovie(movieId: Int): Completable =
        Completable.fromAction {
            movieLocalSource
                .transaction {
                    deleteMovie(movieId)
                    genreLocalSource.deleteGenres(movieId)
                }
        }
}