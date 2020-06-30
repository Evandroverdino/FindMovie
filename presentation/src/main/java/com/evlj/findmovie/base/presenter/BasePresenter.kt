package com.evlj.findmovie.base.presenter

import com.evlj.findmovie.base.activity.IBaseActivity
import com.evlj.findmovie.domain.executors.IDispatcherProvider
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent

abstract class BasePresenter<View : IBaseActivity>(dispatcherProvider: IDispatcherProvider) :
    IBasePresenter<View>, KoinComponent {

    private var internalView: View? = null
    private val compositeDisposable = CompositeDisposable()
    private val coroutineSupervisor by lazy { SupervisorJob() }

    protected val coroutineScope = CoroutineScope(dispatcherProvider.main + coroutineSupervisor)

    override val view: View
        get() = checkNotNull(internalView) {
            "View instance was null. Make sure to call view after attachView() and before " +
                    "detachView()."
        }

    override fun attachView(view: View) {
        internalView = view
        onViewAttached(view)
    }

    override fun detachView() {
        onDetachView()
        compositeDisposable.clear()
        internalView = null
    }

    open fun onViewAttached(view: View) {}

    open fun onDetachView() {
        coroutineSupervisor.cancel()
    }

    fun Disposable.disposeOnDestroy(): Disposable {
        return apply { compositeDisposable.add(this) }
    }

    fun <T> Single<T>.observeOnUi(): Single<T> {
        return observeOn(AndroidSchedulers.mainThread())
    }

    fun Completable.observeOnUi(): Completable {
        return observeOn(AndroidSchedulers.mainThread())
    }
}