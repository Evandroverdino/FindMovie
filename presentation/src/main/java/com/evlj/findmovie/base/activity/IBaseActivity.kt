package com.evlj.findmovie.base.activity

import android.support.annotation.StringRes

interface IBaseActivity {

    fun showMessage(@StringRes messageId: Int)

    fun showMessage(message: String?)
}