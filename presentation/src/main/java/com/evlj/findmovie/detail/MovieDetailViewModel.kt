package com.evlj.findmovie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import com.evlj.findmovie.domain.interactors.DatabaseUseCases
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.model.PMovieDetail
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val dispatcherProvider: IDispatcherProvider,
    private val movieUseCases: MovieUseCases,
    private val databaseUseCases: DatabaseUseCases,
    private val movieDetailMapper: PMovieDetailMapper
) : ViewModel() {

    private val movieDetail: MutableLiveData<PMovieDetail> by lazy { MutableLiveData<PMovieDetail>() }
    private val movieIsFavorite: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val progressBarVisibility: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun loadMovieDetails(movieId: Int, language: String) {
        viewModelScope.launch {
            try {
                progressBarVisibility.postValue(true)
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .searchMovie(movieId)
                        .await()
                        .let(movieDetailMapper::transform)
                }.let {
                    progressBarVisibility.postValue(false)
                    movieDetail.postValue(it)
                    movieIsFavorite.postValue(true)
                }
            } catch (exception: Exception) {
                loadFromAPI(movieId, language)
            }
        }
    }

    fun saveOrDeleteFavoriteMovie(movieDetail: PMovieDetail) {
        viewModelScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .searchMovie(movieDetail.id)
                        .await()
                        .let(movieDetailMapper::transform)
                }.let {
                    deleteMovie(movieDetail.id)
                }
            } catch (exception: Exception) {
                saveMovie(movieDetail)
            }
        }
    }

    private fun loadFromAPI(movieId: Int, language: String) {
        viewModelScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    movieUseCases
                        .getMovieDetails(movieId, language)
                        .await()
                        .let(movieDetailMapper::transform)
                }.let {
                    progressBarVisibility.postValue(false)
                    movieDetail.postValue(it)
                    movieIsFavorite.postValue(false)
                }
            } catch (exception: Exception) {
                progressBarVisibility.postValue(false)
                error.postValue(exception)
            }
        }
    }

    private fun saveMovie(movieDetail: PMovieDetail) {
        viewModelScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .saveMovie(movieDetail.let(movieDetailMapper::parseBack))
                        .await()
                }.let {
                    movieIsFavorite.postValue(true)
                }
            } catch (exception: Exception) {
                error.postValue(exception)
            }
        }
    }

    private fun deleteMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .deleteMovie(movieId)
                        .await()
                }.let {
                    movieIsFavorite.postValue(false)
                }
            } catch (exception: Exception) {
                error.postValue(exception)
            }
        }
    }

    fun getMovieDetails(): LiveData<PMovieDetail> = movieDetail
    fun getMovieIsFavorite(): LiveData<Boolean> = movieIsFavorite
    fun getProgressState(): LiveData<Boolean> = progressBarVisibility
    fun getError(): LiveData<Exception> = error

}