package com.evlj.findmovie.list

import com.evlj.findmovie.base.activity.IBaseView
import com.evlj.findmovie.base.presenter.IBasePresenter
import com.evlj.findmovie.list.listener.RecyclerScrollListener
import com.evlj.findmovie.model.PMovie

interface MainContract {

    interface View : IBaseView {

        fun populateAdapter(results: List<PMovie>)

        fun loadMorePopularMovies(pageResult: Int)

        fun navigateToMovieDetail(movieId: Int)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter : IBasePresenter<View> {

        fun loadPopularMovies(page: Int)

        fun onMovieClicked(movieId: Int)

        fun getScrollListener(): RecyclerScrollListener
    }
}