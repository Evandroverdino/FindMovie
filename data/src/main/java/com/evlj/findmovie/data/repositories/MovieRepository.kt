package com.evlj.findmovie.data.repositories

import com.evlj.findmovie.data.mappers.DiscoverMapper
import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.local.dao.IGenreLocalSource
import com.evlj.findmovie.data.sources.local.dao.IMovieLocalSource
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import com.evlj.findmovie.domain.repositories.IMovieRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val dispatcher: IDispatcherProvider,
    private val movieLocalSource: IMovieLocalSource,
    private val genreLocalSource: IGenreLocalSource,
    private val dataRemoteSource: IDataRemoteSource,
    private val discoverMapper: DiscoverMapper,
    private val movieDetailMapper: MovieDetailsMapper
) : IMovieRepository {

    override suspend fun getPopularMovies(page: Int): Deferred<Discover> =
        withContext(dispatcher.background) {
            async {
                dataRemoteSource
                    .getPopularMovies(page)
                    .let(discoverMapper::transform)
            }
        }

    override suspend fun getMovieDetails(movieId: Int): Deferred<MovieDetail> =
        withContext(dispatcher.background) {
            async {
                genreLocalSource
                    .searchGenres(movieId)
                    .let { genres ->
                        if (genres.isNotEmpty())
                            movieLocalSource
                                .searchMovie(movieId)
                                .copy(_genres = genres)
                                .let(movieDetailMapper::transform)
                        else
                            dataRemoteSource
                                .getMovieDetails(movieId)
                                .apply { isFavorite = false }
                                .let(movieDetailMapper::transform)
                    }
            }
        }

    override suspend fun saveMovie(movieDetail: MovieDetail) =
        withContext(dispatcher.background) {
            async {
                movieLocalSource
                    .transaction {
                        movieDetail.let(movieDetailMapper::parseBack).let {
                            insert(it)
                            it.genres.forEach(genreLocalSource::insert)
                        }
                    }
            }
        }

    override suspend fun deleteMovie(movieId: Int) =
        withContext(dispatcher.background) {
            async {
                movieLocalSource
                    .transaction {
                        deleteMovie(movieId)
                        genreLocalSource.deleteGenres(movieId)
                    }
            }
        }
}