package com.evlj.findmovie.shared.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl"])
fun ImageView.loadImage(url: String?) {
    Picasso.get().load(url).into(this)
}