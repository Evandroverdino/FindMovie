package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DMovie
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.MovieApi
import com.evlj.findmovie.data.sources.paginated.PaginatedSource
import com.evlj.findmovie.data.sources.remote.mappers.DMovieDetailMapper
import com.evlj.findmovie.data.sources.remote.mappers.DMovieMapper
import io.reactivex.Single

class DataRemoteSource(
    private val movieApi: MovieApi,
    private val movieMapper: DMovieMapper,
    private val movieDetailMapper: DMovieDetailMapper
) : IDataRemoteSource {

    override fun getPopularMovies(): PaginatedSource<DMovie> {
        return PaginatedSource { page ->
            movieApi
                .getPopularMovies(page = page)
                .map { it.results }
                .map(movieMapper::transform)
        }
    }

    override fun getMovieDetails(movieId: Int): Single<DMovieDetail> =
        movieApi
            .getMovieDetails(movieId = movieId)
            .map(movieDetailMapper::transform)
}