package com.evlj.findmovie.base.activity

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(), IBaseActivity {

    override fun showMessage(messageId: Int) {
        showMessage(getString(messageId))
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}