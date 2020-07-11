package com.evlj.findmovie.detail

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import com.evlj.findmovie.domain.interactors.DatabaseUseCases
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.model.PMovieDetail
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailPresenter(
    private val dispatcherProvider: IDispatcherProvider,
    private val movieUseCases: MovieUseCases,
    private val databaseUseCases: DatabaseUseCases,
    private val movieDetailMapper: PMovieDetailMapper
) : BasePresenter<MovieDetailContract.View>(dispatcherProvider), MovieDetailContract.Presenter {

    override fun loadMovieDetails(movieId: Int, language: String) {
        coroutineScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .searchMovie(movieId)
                        .await()
                        .let(movieDetailMapper::transform)
                }.let {
                    renderMovieOnView(it)
                }
            } catch (exception: Exception) {
                loadFromAPI(movieId, language)
            }
        }
    }

    private fun renderMovieOnView(movieDetail: PMovieDetail) = with(movieDetail) {
        view.let {
            it.hideProgressBar()
            it.showMovieDetails(this)
            it.updateFavoriteView(true)
        }
    }

    private fun loadFromAPI(movieId: Int, language: String) {
        coroutineScope.launch {
            try {
                view.showProgressBar()
                withContext(dispatcherProvider.background) {
                    movieUseCases
                        .getMovieDetails(movieId, language)
                        .await()
                        .let(movieDetailMapper::transform)
                }.let {
                    view.hideProgressBar()
                    view.showMovieDetails(it)
                }
            } catch (exception: Exception) {
                view.hideProgressBar()
                view.showMessage(exception.message)
            }
        }
    }

    fun saveOrDeleteFavoriteMovie(movieDetail: PMovieDetail) {
        coroutineScope.launch {
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

    private fun saveMovie(movieDetail: PMovieDetail) {
        coroutineScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .saveMovie(movieDetail.let(movieDetailMapper::parseBack))
                        .await()
                }.let {
                    view.updateFavoriteView(true)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    private fun deleteMovie(movieId: Int) {
        coroutineScope.launch {
            try {
                withContext(dispatcherProvider.background) {
                    databaseUseCases
                        .deleteMovie(movieId)
                        .await()
                }.let {
                    view.updateFavoriteView()
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}