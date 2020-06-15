package com.evlj.findmovie.list

import com.evlj.findmovie.base.activity.IBaseActivity
import com.evlj.findmovie.base.presenter.IBasePresenter
import com.evlj.findmovie.model.PMovie

interface MainContract {

    interface View : IBaseActivity {

        fun populateAdapter(results: List<PMovie>)

        fun loadMorePopularMovies(pageResult: Int)

        fun navigateToMovieDetail(movieId: Int)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter :
        IBasePresenter<View> {

        fun loadPopularMovies(
            apiKey: String, language: String,
            sortBy: String, includeAdult: Boolean,
            includeVideo: Boolean, page: Int
        )
    }
}