package com.evlj.findmovie.base.presenter

interface IBasePresenter<View> {

    val view: View

    fun attachView(view: View)

    fun detachView()
}