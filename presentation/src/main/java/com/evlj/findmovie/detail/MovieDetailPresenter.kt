package com.evlj.findmovie.detail

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.interactors.DatabaseUseCases
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.model.PMovieDetail
import io.reactivex.rxkotlin.subscribeBy

class MovieDetailPresenter(
    private val movieUseCases: MovieUseCases,
    private val databaseUseCases: DatabaseUseCases,
    private val movieDetailMapper: PMovieDetailMapper
) : BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {

    override fun loadMovieDetails(movieId: Int, apiKey: String, language: String) {
        databaseUseCases
            .searchMovie(movieId)
            .map(movieDetailMapper::transform)
            .observeOnUi()
            .subscribeBy(
                onSuccess = { renderMovieOnView(it) },
                onError = { loadFromAPI(movieId, apiKey, language) }
            )
            .disposeOnDestroy()
    }

    private fun renderMovieOnView(movieDetail: PMovieDetail) = with(movieDetail) {
        view.let {
            it.hideProgressBar()
            it.showMovieDetails(this)
            it.updateFavoriteView(true)
        }
    }

    private fun loadFromAPI(movieId: Int, apiKey: String, language: String) =
        movieUseCases
            .getMovieDetails(movieId, apiKey, language)
            .map(movieDetailMapper::transform)
            .observeOnUi()
            .doOnSubscribe { view.showProgressBar() }
            .subscribeBy(
                onSuccess = {
                    view.hideProgressBar()
                    view.showMovieDetails(it)
                },
                onError = {
                    view.hideProgressBar()
                    view.showMessage(it.message)
                }
            )
            .disposeOnDestroy()

    fun saveOrDeleteFavoriteMovie(movieDetail: PMovieDetail) {
        databaseUseCases
            .searchMovie(movieDetail.id)
            .map(movieDetailMapper::transform)
            .observeOnUi()
            .subscribeBy(
                onSuccess = { deleteMovie(movieDetail.id) },
                onError = { saveMovie(movieDetail) }
            )
            .disposeOnDestroy()
    }

    private fun saveMovie(movieDetail: PMovieDetail) {
        databaseUseCases
            .saveMovie(movieDetail.let(movieDetailMapper::parseBack))
            .observeOnUi()
            .subscribeBy(
                onComplete = { view.updateFavoriteView(true) },
                onError = { it.printStackTrace() }
            )
            .disposeOnDestroy()
    }

    private fun deleteMovie(movieId: Int) {
        databaseUseCases
            .deleteMovie(movieId)
            .observeOnUi()
            .subscribeBy(
                onComplete = { view.updateFavoriteView() },
                onError = { it.printStackTrace() }
            )
            .disposeOnDestroy()
    }
}