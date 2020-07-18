package com.evlj.findmovie.data.repositories

import android.arch.persistence.room.EmptyResultSetException
import com.evlj.findmovie.data.mappers.DiscoverMapper
import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.local.dao.IGenreLocalSource
import com.evlj.findmovie.data.sources.local.dao.IMovieLocalSource
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IMovieRepository
import io.reactivex.Completable
import io.reactivex.Single

class MovieRepository(
    private val movieLocalSource: IMovieLocalSource,
    private val genreLocalSource: IGenreLocalSource,
    private val dataRemoteSource: IDataRemoteSource,
    private val discoverMapper: DiscoverMapper,
    private val movieDetailMapper: MovieDetailsMapper
) : IMovieRepository {

    override fun getPopularMovies(page: Int): Single<Discover> =
        dataRemoteSource
            .getPopularMovies(page)
            .map(discoverMapper::transform)

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> =
        genreLocalSource
            .searchGenres(movieId)
            .flatMap { genres ->
                movieLocalSource
                    .searchMovie(movieId)
                    .map { it.copy(_genres = genres) }
            }
            .onErrorResumeNext { throwable ->
                when (throwable) {
                    is EmptyResultSetException -> dataRemoteSource
                        .getMovieDetails(movieId)
                        .map { it.apply { isFavorite = false } }
                    else -> Single.error(throwable)
                }
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