package com.evlj.findmovie.detail

import com.evlj.findmovie.base.BaseContract
import com.evlj.findmovie.model.MovieDetail

interface MovieDetailContract : BaseContract {

    fun showMovieDetails(movieDetail: MovieDetail)

    fun showProgressBar()

    fun hideProgressBar()

}