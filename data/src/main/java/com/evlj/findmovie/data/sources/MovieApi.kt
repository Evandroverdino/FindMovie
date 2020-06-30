package com.evlj.findmovie.data.sources

import com.evlj.findmovie.data.sources.remote.entities.RDiscover
import com.evlj.findmovie.data.sources.remote.entities.RMovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: Int
    ): RDiscover

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<RMovieDetail>

}