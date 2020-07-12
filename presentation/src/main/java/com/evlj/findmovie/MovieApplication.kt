package com.evlj.findmovie

import android.app.Application
import com.evlj.findmovie.data.di.*
import com.evlj.findmovie.di.presentationAppModule
import com.evlj.findmovie.di.presentationMapperModule
import com.evlj.findmovie.di.presentationViewModelModule
import com.evlj.findmovie.domain.di.domainUseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        buildKoinInstance()
    }

    private fun buildKoinInstance() {
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    presentationAppModule,
                    presentationMapperModule,
                    presentationViewModelModule,
                    domainUseCaseModule,
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