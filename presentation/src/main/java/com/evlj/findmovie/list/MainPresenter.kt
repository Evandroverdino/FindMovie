package com.evlj.findmovie.list

import com.evlj.findmovie.base.presenter.BasePresenter
import com.evlj.findmovie.domain.entities.paginated.PaginationCommand
import com.evlj.findmovie.domain.interactors.MovieUseCases
import com.evlj.findmovie.list.listener.RecyclerScrollListener
import com.evlj.findmovie.mappers.PMovieMapper
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

class MainPresenter(
    private val movieUseCases: MovieUseCases,
    private val movieMapper: PMovieMapper
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val publishSubject = PublishSubject.create<PaginationCommand>()

    override fun loadPopularMovies() {
        movieUseCases
            .getPopularMovies(publishSubject.startWith(PaginationCommand.GetNext))
            .map(movieMapper::transform)
            .observeOnUi()
            .doOnSubscribe { view.showProgressBar() }
            .doOnNext { view.hideProgressBar() }
            .doOnError { view.hideProgressBar() }
            .subscribeBy(
                onNext = { view.populateAdapter(it) },
                onError = { view.showMessage(it.message) }
            )
            .disposeOnDestroy()
    }

    override fun onMovieClicked(movieId: Int) = view.navigateToMovieDetail(movieId)

    override fun getScrollListener(): RecyclerScrollListener = object : RecyclerScrollListener() {
        override fun loadMoreData() {
            publishSubject.onNext(PaginationCommand.GetNext)
        }
    }
}