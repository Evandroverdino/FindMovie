package com.evlj.findmovie.base

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.evlj.findmovie.R

abstract class BaseActivity : AppCompatActivity(), BaseContract {

    private val alertDialog: AlertDialog by lazy {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(getString(R.string.loading))
        dialog.setCancelable(true)
        dialog.create()
    }

    override fun showMessage(messageId: Int) {
        showMessage(getString(messageId))
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        alertDialog.show()
    }

    override fun dismissLoading() {
        alertDialog.dismiss()
    }

}