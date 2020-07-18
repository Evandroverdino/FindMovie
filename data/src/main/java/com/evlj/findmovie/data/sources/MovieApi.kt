package com.evlj.findmovie.data.sources

import com.evlj.findmovie.data.helpers.Constants
import com.evlj.findmovie.data.sources.remote.entities.RDiscover
import com.evlj.findmovie.data.sources.remote.entities.RMovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.API_LANGUAGE,
        @Query("sort_by") sortBy: String = Constants.API_SORT_BY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("page") page: Int
    ): Single<RDiscover>

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.API_LANGUAGE
    ): Single<RMovieDetail>

}