package com.evlj.findmovie.detail

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.interactors.DataUseCases
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.model.PMovieDetail
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter(
    private val dataUseCases: DataUseCases,
    private val movieDetailMapper: PMovieDetailMapper
) : BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {

    override fun loadMovieDetails(movieId: Int, apiKey: String, language: String) {
        dataUseCases
            .searchMovieInDatabase(movieId)
            .map(movieDetailMapper::transform)
            .observeOnUi()
            .subscribeBy(
                onSuccess = { loadFromRealm(it) },
                onError = { loadFromAPI(movieId, apiKey, language) }
            ).disposeOnDestroy()
    }

    private fun loadFromRealm(movieDetail: PMovieDetail) = with(movieDetail) {
        view.let {
            it.hideProgressBar()
            it.showMovieDetails(this)
            it.updateFavoriteView(isFavorite)
        }
    }

    private fun loadFromAPI(movieId: Int, apiKey: String, language: String) =
        dataUseCases
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
            ).disposeOnDestroy()

    fun saveOrDeleteFavoriteMovie(movieDetail: PMovieDetail) {
        dataUseCases
            .searchMovieInDatabase(movieDetail.id)
            .map(movieDetailMapper::transform)
            .observeOnUi()
            .subscribeBy(
                onSuccess = { setMovieAsFavoriteOrNot(movieDetail) },
                onError = { it.printStackTrace() }
            ).disposeOnDestroy()
    }

    private fun setMovieAsFavoriteOrNot(movieDetail: PMovieDetail) {
        dataUseCases
            .setMovieAsFavoriteOrNot(movieDetail.let(movieDetailMapper::parseBack))
            .subscribeOn(Schedulers.io())
            .observeOnUi()
            .subscribeBy(
                onComplete = { view.updateFavoriteView(movieDetail.isFavorite) },
                onError = { it.printStackTrace() }
            ).disposeOnDestroy()
    }
}