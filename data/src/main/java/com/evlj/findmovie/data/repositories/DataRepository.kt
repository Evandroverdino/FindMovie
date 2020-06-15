package com.evlj.findmovie.data.repositories

import com.evlj.findmovie.data.mappers.DiscoverMapper
import com.evlj.findmovie.data.mappers.MovieDetailsMapper
import com.evlj.findmovie.data.sources.local.IDataLocalSource
import com.evlj.findmovie.data.sources.remote.IDataRemoteSource
import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.repositories.IDataRepository
import io.reactivex.Completable
import io.reactivex.Single

class DataRepository(
    private val dataRemoteSource: IDataRemoteSource,
    private val dataLocalSource: IDataLocalSource,
    private val discoverMapper: DiscoverMapper,
    private val movieDetailMapper: MovieDetailsMapper
) : IDataRepository {

    override fun getPopularMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: Int
    ): Single<Discover> =
        dataRemoteSource
            .getPopularMovies(
                apiKey = apiKey,
                language = language,
                sortBy = sortBy,
                includeAdult = includeAdult,
                includeVideo = includeVideo,
                page = page
            )
            .map(discoverMapper::transform)

    override fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<MovieDetail> =
        dataRemoteSource
            .getMovieDetails(
                movieId = movieId,
                apiKey = apiKey,
                language = language
            )
            .map(movieDetailMapper::transform)

    override fun searchMovieInDatabase(movieId: Int): Single<MovieDetail> =
        dataLocalSource
            .searchMovieInDatabase(movieId)
            .map(movieDetailMapper::transform)

    override fun setMovieAsFavoriteOrNot(movieDetail: MovieDetail): Completable =
        dataLocalSource
            .setMovieAsFavoriteOrNot(movieDetail.let(movieDetailMapper::parseBack))
}