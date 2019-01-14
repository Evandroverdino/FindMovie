package com.evlj.findmovie.network.factory

import com.evlj.findmovie.shared.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseFactory<T>(clazz: Class<T>) {

    val movieApi: T by lazy {
        createBaseRetrofitBuilder()
            .client(createOkHttpClient().build())
            .build().create<T>(clazz)
    }

    private fun createBaseRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    abstract fun createOkHttpClient(): OkHttpClient.Builder

}