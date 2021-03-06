package com.evlj.findmovie.network.api

import com.evlj.findmovie.model.Discover
import com.evlj.findmovie.model.MovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: Int
    ): Single<Discover>

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieDetail>

}