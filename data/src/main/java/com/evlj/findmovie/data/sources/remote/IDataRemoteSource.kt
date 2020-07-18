package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import io.reactivex.Single

interface IDataRemoteSource {

    fun getPopularMovies(page: Int): Single<DDiscover>

    fun getMovieDetails(movieId: Int): Single<DMovieDetail>
}