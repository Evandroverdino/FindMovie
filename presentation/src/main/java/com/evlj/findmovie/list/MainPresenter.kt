package com.evlj.findmovie.list

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.list.listener.RecyclerScrollListener
import com.evlj.findmovie.mappers.PDiscoverMapper
import io.reactivex.rxkotlin.subscribeBy

class MainPresenter(
    private val movieUseCases: MovieUseCases,
    private val discoverMapper: PDiscoverMapper
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    var pageResult: Int = 0
    var totalPageResults: Int = 0

    override fun loadPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ) {
        movieUseCases
            .getPopularMovies(
                apiKey = apiKey,
                language = language,
                sortBy = sortBy,
                includeAdult = includeAdult,
                includeVideo = includeVideo,
                page = page
            )
            .map(discoverMapper::transform)
            .observeOnUi()
            .doOnSubscribe { view.showProgressBar() }
            .subscribeBy(
                onSuccess = {
                    view.hideProgressBar()
                    view.populateAdapter(it.results)
                    totalPageResults = it.totalPages
                },
                onError = {
                    view.hideProgressBar()
                    view.showMessage(it.message)
                }
            ).disposeOnDestroy()

        pageResult = page
    }

    fun getScrollListener(): RecyclerScrollListener = object : RecyclerScrollListener() {
        override fun loadMoreMovies() {
            if (pageResult + 1 <= totalPageResults) {
                view.loadMorePopularMovies(pageResult + 1)
            }
        }
    }

    fun onClickMovie(movieId: Int) = view.navigateToMovieDetail(movieId)
}