package com.evlj.findmovie.data.sources.remote

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.entities.DMovieDetail
import kotlinx.coroutines.Deferred

interface IDataRemoteSource {

    suspend fun getPopularMovies(page: Int): Deferred<DDiscover>

    suspend fun getMovieDetails(movieId: Int): Deferred<DMovieDetail>
}