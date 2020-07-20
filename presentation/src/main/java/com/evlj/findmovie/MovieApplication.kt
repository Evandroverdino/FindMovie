package com.evlj.findmovie

import android.app.Application
import com.evlj.findmovie.data.di.*
import com.evlj.findmovie.di.presentationMapperModule
import com.evlj.findmovie.di.presentationViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    presentationMapperModule,
                    presentationViewModelModule,
                    dataAppModule,
                    dataMapperModule,
                    dataNetworkModule,
                    dataRepositoryModule,
                    dataSourceModule
                )
            )
        }
    }
}