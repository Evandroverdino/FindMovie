package com.evlj.findmovie.shared.extensions

import android.view.View

fun View.makeVisibleIf(predicate: Boolean = true, otherwiseState: Int = View.GONE) {
    visibility = if (predicate) View.VISIBLE else otherwiseState
}

fun View.makeGoneIf(predicate: Boolean = true) {
    makeVisibleIf(!predicate)
}