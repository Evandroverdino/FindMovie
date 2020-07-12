package com.evlj.findmovie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.mappers.PDiscoverMapper
import com.evlj.findmovie.model.PMovie
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val dispatcherProvider: IDispatcherProvider,
    private val movieUseCases: MovieUseCases,
    private val discoverMapper: PDiscoverMapper
) : ViewModel() {

    private val totalPageResults: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    private val movies: MutableLiveData<List<PMovie>> by lazy { MutableLiveData<List<PMovie>>() }
    private val progressBarVisibility: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun loadPopularMovies(
        language: String,
        sortBy: String,
        page: Int
    ) {
        viewModelScope.launch {
            try {
                progressBarVisibility.postValue(true)
                withContext(dispatcherProvider.background) {
                    movieUseCases
                        .getPopularMovies(
                            language = language,
                            sortBy = sortBy,
                            page = page
                        )
                        .await()
                        .let(discoverMapper::transform)
                }.let {
                    progressBarVisibility.postValue(false)
                    movies.postValue(it.results)
                    totalPageResults.postValue(it.totalPages)
                }
            } catch (exception: Exception) {
                progressBarVisibility.postValue(false)
                error.postValue(exception)
            }
        }
    }

    fun getMovies(): LiveData<List<PMovie>> = movies
    fun getPageResults(): LiveData<Int> = totalPageResults
    fun getProgressState(): LiveData<Boolean> = progressBarVisibility
    fun getError(): LiveData<Exception> = error
}