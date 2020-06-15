package com.evlj.findmovie.data.di

import com.evlj.findmovie.data.helpers.Constants
import com.evlj.findmovie.data.helpers.Constants.HTTP_INTERCEPTOR
import com.evlj.findmovie.data.helpers.RetrofitFactory
import com.evlj.findmovie.data.sources.MovieApi
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataNetworkModule = module {
    single { Gson() }
    single<Interceptor>(named(HTTP_INTERCEPTOR)) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get(named(HTTP_INTERCEPTOR)))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single { RetrofitFactory.create<MovieApi>(get()) }
}