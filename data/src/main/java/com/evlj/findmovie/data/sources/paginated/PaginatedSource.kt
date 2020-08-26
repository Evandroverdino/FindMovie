package com.evlj.findmovie.data.sources.paginated

import com.evlj.findmovie.domain.entities.paginated.PaginationCommand
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class PaginatedSource<T>(
    private val requestBlock: (page: Int) -> Single<List<T>>
) : Consumer<PaginationCommand> {

    private val publishSubject = PublishSubject.create<Page>()
    private val disposeBag = CompositeDisposable()

    private var currPage: Int = INITIAL_PAGE

    private val observable by lazy {
        publishSubject
            .distinct()
            .flatMapSingle { page ->
                requestBlock(page.number)
                    .subscribeOn(Schedulers.io())
                    .map { page to it }
            }
            .map { (page, items) ->
                currPage = page.number

                if (items.size < page.perPage) publishSubject.onComplete()

                items
            }
            .doOnDispose { disposeBag.clear() }
    }

    override fun accept(command: PaginationCommand) {
        when (command) {
            is PaginationCommand.GetNext ->
                publishSubject.onNext(Page(currPage + 1))
        }
    }

    fun asObservable(commandSource: Observable<PaginationCommand>): Observable<List<T>> {
        Observable
            .timer(200, TimeUnit.MILLISECONDS)
            .subscribe {
                commandSource
                    .subscribe(this@PaginatedSource)
                    .addTo(disposeBag)
            }
            .addTo(disposeBag)

        return observable
    }

    private data class Page(
        val number: Int,
        val perPage: Int = DEFAULT_PER_PAGE
    )

    companion object {
        private const val INITIAL_PAGE = 0
        private const val DEFAULT_PER_PAGE = 20
    }
}