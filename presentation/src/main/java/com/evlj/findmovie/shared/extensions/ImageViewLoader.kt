package com.evlj.findmovie.shared.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    url?.let { Picasso.get().load(it).into(this) }
}