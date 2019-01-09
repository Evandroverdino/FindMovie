package com.evlj.findmovie.list

import com.evlj.findmovie.base.BaseContract
import com.evlj.findmovie.model.Movie

interface MainContract : BaseContract {

    fun fillPopularMovies(results: List<Movie>)

    fun loadMorePopularMovies(pageResult: Int)

    fun navigateToMovieDetail(movieId: Int)

}