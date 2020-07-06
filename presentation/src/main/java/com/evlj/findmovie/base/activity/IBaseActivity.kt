package com.evlj.findmovie.base.activity

import androidx.annotation.StringRes

interface IBaseActivity {

    fun showMessage(@StringRes messageId: Int)

    fun showMessage(message: String?)
}