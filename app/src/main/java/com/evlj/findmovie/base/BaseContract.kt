package com.evlj.findmovie.base

import android.support.annotation.StringRes

interface BaseContract {

    fun showMessage(@StringRes messageId: Int)

    fun showMessage(message: String?)

    fun showLoading()

    fun dismissLoading()

}