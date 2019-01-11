package com.evlj.findmovie.list

import com.evlj.findmovie.base.BasePresenter
import com.evlj.findmovie.list.listener.RecyclerScrollListener
import com.evlj.findmovie.network.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainContract>(MainContract::class.java) {

    val movieService: MovieService by lazy { MovieService() }
    var pageResult: Int = 0
    var totalPageResults: Int = 0

    fun loadPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ) {
        movieService.getPopularMovies(
            apiKey, language, sortBy,
            includeAdult, includeVideo, page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showProgressBar() }
            .subscribe({
                view?.hideProgressBar()
                view?.populateAdapter(it.results)
                totalPageResults = it.totalPages
            }, {
                view?.hideProgressBar()
                view?.showMessage(it.message)
            })
        pageResult = page
    }

    fun getScrollListener(): RecyclerScrollListener = object : RecyclerScrollListener() {
        override fun loadMoreMovies() {
            if (pageResult + 1 <= totalPageResults) {
                view?.loadMorePopularMovies(pageResult + 1)
            }
        }
    }

    fun onClickMovie(movieId: Int) =
        view?.navigateToMovieDetail(movieId)

}