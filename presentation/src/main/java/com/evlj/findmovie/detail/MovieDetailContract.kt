package com.evlj.findmovie.detail

import com.evlj.findmovie.base.activity.IBaseActivity
import com.evlj.findmovie.base.presenter.IBasePresenter
import com.evlj.findmovie.model.PMovieDetail

interface MovieDetailContract {

    interface View : IBaseActivity {
        fun showMovieDetails(movieDetail: PMovieDetail)

        fun updateFavoriteView(isFavorite: Boolean = false)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter : IBasePresenter<View> {
        fun loadMovieDetails(movieId: Int, language: String)
    }
}