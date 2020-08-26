package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DMovie
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.paginated.PaginatedSource
import io.reactivex.Single

interface IDataRemoteSource {

    fun getPopularMovies(): PaginatedSource<DMovie>

    fun getMovieDetails(movieId: Int): Single<DMovieDetail>
}