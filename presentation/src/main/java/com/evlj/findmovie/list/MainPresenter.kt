package com.evlj.findmovie.list

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.list.listener.RecyclerScrollListener
import com.evlj.findmovie.mappers.PDiscoverMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
    private val movieUseCases: MovieUseCases,
    private val dispatcherProvider: IDispatcherProvider,
    private val discoverMapper: PDiscoverMapper
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    var pageResult: Int = 0
    var totalPageResults: Int = 0

    private val coroutineSupervisor by lazy { SupervisorJob() }
    private val coroutineScope = CoroutineScope(dispatcherProvider.main + coroutineSupervisor)

    override fun onDetachView() {
        super.onDetachView()
        coroutineSupervisor.cancel()
    }

    override fun loadPopularMovies(
        apiKey: String, language: String,
        sortBy: String, includeAdult: Boolean,
        includeVideo: Boolean, page: Int
    ) {
        coroutineScope.launch {
            try {
                view.showProgressBar()
                withContext(dispatcherProvider.background) {
                    movieUseCases
                        .getPopularMovies(
                            apiKey = apiKey,
                            language = language,
                            sortBy = sortBy,
                            includeAdult = includeAdult,
                            includeVideo = includeVideo,
                            page = page
                        )
                        .await()
                        .let(discoverMapper::transform)
                }.let {
                    view.hideProgressBar()
                    view.populateAdapter(it.results)
                    totalPageResults = it.totalPages
                }
            } catch (exception: Exception) {
                view.hideProgressBar()
                view.showMessage(exception.message)
            }
        }

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