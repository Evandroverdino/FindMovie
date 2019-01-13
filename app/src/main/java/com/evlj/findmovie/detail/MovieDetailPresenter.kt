package com.evlj.findmovie.detail

import com.evlj.findmovie.base.BasePresenter
import com.evlj.findmovie.model.MovieDetail
import com.evlj.findmovie.network.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter : BasePresenter<MovieDetailContract>(MovieDetailContract::class.java) {

    private val movieService: MovieService by lazy { MovieService() }

    fun loadMovieDetails(movieId: Int, apiKey: String, language: String) {
        movieService.getMovieDetails(movieId, apiKey, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showProgressBar() }
            .subscribe({
                view?.hideProgressBar()
                view?.showMovieDetails(it)
            }, {
                view?.hideProgressBar()
                view?.showMessage(it.message)
            })
    }

    fun saveOrDeleteFavoriteMovie(movieDetail: MovieDetail) {
        view?.checkOrUncheckMovie()
    }

}