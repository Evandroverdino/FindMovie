package com.evlj.findmovie.shared.extensions

import com.evlj.findmovie.BuildConfig

fun <T> tryOrNull(block: () -> T) =
    try {
        block()
    } catch (exception: Exception) {
        if (BuildConfig.DEBUG) exception.printStackTrace()
        null
    }