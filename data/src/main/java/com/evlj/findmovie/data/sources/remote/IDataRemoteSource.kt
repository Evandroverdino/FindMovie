package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail

interface IDataRemoteSource {

    suspend fun getPopularMovies(page: Int): DDiscover

    suspend fun getMovieDetails(movieId: Int): DMovieDetail
}