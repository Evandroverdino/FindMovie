package com.evlj.findmovie.detail

import com.evlj.findmovie.base.activity.IBaseActivity
import com.evlj.findmovie.model.PMovieDetail

interface MovieDetailContract {

    interface View : IBaseActivity {

        fun showMovieDetails(movieDetail: PMovieDetail)

        fun updateFavoriteView(isFavorite: Boolean)
    }

    //TODO Add interface and implement on viewModel
}