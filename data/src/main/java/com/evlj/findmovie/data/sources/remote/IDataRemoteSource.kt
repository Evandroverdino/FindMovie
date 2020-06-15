package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import io.reactivex.Single

interface IDataRemoteSource {

    fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Single<DDiscover>

    fun getMovieDetails(
        movieId: Int, apiKey: String,
        language: String
    ): Single<DMovieDetail>
}