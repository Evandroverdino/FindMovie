package com.evlj.findmovie.network.factory

import com.evlj.findmovie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

abstract class ServiceFactory<T>(clazz: Class<T>) : BaseFactory<T>(clazz) {

    override fun createOkHttpClient(): OkHttpClient.Builder {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val networkInterceptor = HttpLoggingInterceptor()
            networkInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(networkInterceptor)
        }
        return client
    }

}