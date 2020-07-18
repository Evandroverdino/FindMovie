package com.evlj.findmovie.base.activity

import android.support.annotation.StringRes

interface IBaseView {

    fun showMessage(@StringRes messageId: Int)

    fun showMessage(message: String?)
}