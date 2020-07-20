package com.evlj.findmovie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evlj.findmovie.domain.extensions.launchJob
import com.evlj.findmovie.domain.repositories.IMovieRepository
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.model.PMovieDetail

class MovieDetailViewModel(
    private val movieRepository: IMovieRepository,
    private val movieDetailMapper: PMovieDetailMapper
) : ViewModel() {

    private val movieDetail: MutableLiveData<PMovieDetail> by lazy { MutableLiveData<PMovieDetail>() }
    private val movieIsFavorite: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val progressBarVisibility: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launchJob(
            requestBlock = {
                progressBarVisibility.postValue(true)
                movieRepository
                    .getMovieDetails(movieId)
                    .await()
                    .let(movieDetailMapper::transform)
            },
            onSuccess = {
                progressBarVisibility.postValue(false)
                movieDetail.postValue(it)
            },
            onFailure = {
                progressBarVisibility.postValue(false)
                error.postValue(it as Exception?)
            }
        )
    }

    fun saveOrDeleteFavoriteMovie(movieDetail: PMovieDetail) {
        when {
            movieDetail.isFavorite -> deleteMovie(movieDetail.id)
            else -> saveMovie(movieDetail)
        }
    }

    private fun saveMovie(movieDetail: PMovieDetail) {
        viewModelScope.launchJob(
            requestBlock = {
                movieRepository
                    .saveMovie(movieDetail.let(movieDetailMapper::parseBack))
                    .await()
            },
            onSuccess = {
                movieIsFavorite.postValue(true)
            },
            onFailure = {
                error.postValue(it as Exception?)
            }
        )
    }

    private fun deleteMovie(movieId: Int) {
        viewModelScope.launchJob(
            requestBlock = {
                movieRepository
                    .deleteMovie(movieId)
                    .await()
            },
            onSuccess = {
                movieIsFavorite.postValue(false)
            },
            onFailure = {
                error.postValue(it as Exception?)
            }
        )
    }

    fun getMovieDetails(): LiveData<PMovieDetail> = movieDetail
    fun getMovieIsFavorite(): LiveData<Boolean> = movieIsFavorite
    fun getProgressState(): LiveData<Boolean> = progressBarVisibility
    fun getError(): LiveData<Exception> = error

}