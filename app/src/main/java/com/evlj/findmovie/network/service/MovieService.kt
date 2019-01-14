package com.evlj.findmovie.network.service

import com.evlj.findmovie.model.Discover
import com.evlj.findmovie.model.MovieDetail
import com.evlj.findmovie.network.api.MovieApi
import com.evlj.findmovie.network.factory.ServiceFactory
import io.reactivex.Single

class MovieService : ServiceFactory<MovieApi>(MovieApi::class.java) {

    fun getPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ): Single<Discover> = movieApi.getPopularMovies(
        apiKey, language, sortBy,
        includeAdult, includeVideo, page
    )

    fun getMovieDetails(
        movieId: Int, apiKey: String,
        language: String
    ): Single<MovieDetail> = movieApi.getMovieDetails(movieId, apiKey, language)

}