package com.evlj.findmovie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evlj.findmovie.domain.extensions.launchJob
import com.evlj.findmovie.domain.repositories.IMovieRepository
import com.evlj.findmovie.mappers.PDiscoverMapper
import com.evlj.findmovie.model.PMovie

class MainViewModel(
    private val movieRepository: IMovieRepository,
    private val discoverMapper: PDiscoverMapper
) : ViewModel() {

    private val totalPageResults: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    private val movies: MutableLiveData<List<PMovie>> by lazy { MutableLiveData<List<PMovie>>() }
    private val progressBarVisibility: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun loadPopularMovies(page: Int) {
        viewModelScope.launchJob(
            requestBlock = {
                progressBarVisibility.postValue(true)
                movieRepository
                    .getPopularMovies(page)
                    .await()
                    .let(discoverMapper::transform)
            },
            onSuccess = {
                progressBarVisibility.postValue(false)
                movies.postValue(it.results)
                totalPageResults.postValue(it.totalPages)
            },
            onFailure = {
                progressBarVisibility.postValue(false)
                error.postValue(it as Exception?)
            }
        )
    }

    fun getMovies(): LiveData<List<PMovie>> = movies
    fun getPageResults(): LiveData<Int> = totalPageResults
    fun getProgressState(): LiveData<Boolean> = progressBarVisibility
    fun getError(): LiveData<Exception> = error
}