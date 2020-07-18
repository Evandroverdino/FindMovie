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

    override fun loadPopularMovies(page: Int) {
        movieUseCases
            .getPopularMovies(page)
            .map(discoverMapper::transform)
            .observeOnUi()
            .doOnSubscribe { view.showProgressBar() }
            .doAfterTerminate { view.hideProgressBar() }
            .subscribeBy(
                onSuccess = {
                    view.populateAdapter(it.results)
                    totalPageResults = it.totalPages
                },
                onError = { view.showMessage(it.message) }
            )
            .disposeOnDestroy()

        pageResult = page
    }

    override fun onMovieClicked(movieId: Int) = view.navigateToMovieDetail(movieId)

    override fun getScrollListener(): RecyclerScrollListener = object : RecyclerScrollListener() {
        override fun loadMoreData() {
            if (pageResult + 1 <= totalPageResults) {
                view.loadMorePopularMovies(pageResult + 1)
            }
        }
    }
}