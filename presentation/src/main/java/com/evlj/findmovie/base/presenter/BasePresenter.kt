package com.evlj.findmovie.base.presenter

import com.evlj.findmovie.base.activity.IBaseActivity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent

abstract class BasePresenter<View : IBaseActivity> : IBasePresenter<View>, KoinComponent {

    private var internalView: View? = null
    private val compositeDisposable = CompositeDisposable()

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

    open fun onDetachView() {}

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