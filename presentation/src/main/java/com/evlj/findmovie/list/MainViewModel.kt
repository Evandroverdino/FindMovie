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

    fun loadPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ) {
        viewModelScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    movieUseCases
                        .getPopularMovies(
                            apiKey = apiKey,
                            language = language,
                            sortBy = sortBy,
                            includeAdult = includeAdult,
                            includeVideo = includeVideo,
                            page = page
                        )
                        .await()
                        .let(discoverMapper::transform)
                }.let {
                    movies.postValue(it.results)
                    totalPageResults.value = it.totalPages
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun getMovies(): LiveData<List<PMovie>> = movies
    fun getPageResults(): LiveData<Int> = totalPageResults
}