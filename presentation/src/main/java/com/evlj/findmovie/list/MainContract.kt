package com.evlj.findmovie.list

import com.evlj.findmovie.base.activity.IBaseActivity

interface MainContract {

    interface View : IBaseActivity {

        fun loadMorePopularMovies(pageResult: Int)

        fun navigateToMovieDetail(movieId: Int)
    }

    //TODO Add interface and implement on viewModel
}