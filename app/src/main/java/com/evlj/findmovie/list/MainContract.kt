package com.evlj.findmovie.list

import com.evlj.findmovie.base.BaseContract
import com.evlj.findmovie.model.Movie

interface MainContract : BaseContract {

    fun populateAdapter(results: List<Movie>)

    fun loadMorePopularMovies(pageResult: Int)

    fun navigateToMovieDetail(movieId: Int)

    fun showProgressBar()

    fun hideProgressBar()

}