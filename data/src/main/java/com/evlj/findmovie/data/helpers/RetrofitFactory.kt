package com.evlj.findmovie.data.helpers

import retrofit2.Retrofit

object RetrofitFactory {

    @JvmStatic
    inline fun <reified T> create(retrofit: Retrofit): T =
        retrofit.create(T::class.java)
}