package com.evlj.findmovie.detail

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.mappers.PMovieDetailMapper
import com.evlj.findmovie.model.PMovieDetail
import io.reactivex.rxkotlin.subscribeBy

class MovieDetailPresenter(
    private val movieUseCases: MovieUseCases,
    private val movieDetailMapper: PMovieDetailMapper
) : BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {

    override fun loadMovieDetails(movieId: Int) {
        movieUseCases
            .getMovieDetails(movieId)
            .map(movieDetailMapper::transform)
            .observeOnUi()
            .doOnSubscribe { view.showProgressBar() }
            .doAfterTerminate { view.hideProgressBar() }
            .subscribeBy(
                onSuccess = { view.showMovieDetails(it) },
                onError = { view.showMessage(it.message) }
            )
            .disposeOnDestroy()
    }

    override fun saveOrDeleteFavoriteMovie(movieDetail: PMovieDetail) {
        when {
            movieDetail.isFavorite -> deleteMovie(movieDetail.id)
            else -> saveMovie(movieDetail)
        }
    }

    private fun saveMovie(movieDetail: PMovieDetail) {
        movieUseCases
            .saveMovie(movieDetail.let(movieDetailMapper::parseBack))
            .observeOnUi()
            .subscribeBy(
                onComplete = { view.updateFavoriteView(true) },
                onError = { view.showMessage(it.message) }
            )
            .disposeOnDestroy()
    }

    private fun deleteMovie(movieId: Int) {
        movieUseCases
            .deleteMovie(movieId)
            .observeOnUi()
            .subscribeBy(
                onComplete = { view.updateFavoriteView() },
                onError = { view.showMessage(it.message) }
            )
            .disposeOnDestroy()
    }
}