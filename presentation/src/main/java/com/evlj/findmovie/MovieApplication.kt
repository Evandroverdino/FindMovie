package com.evlj.findmovie

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        buildDatabase()
    }

    private fun buildDatabase() = Realm.setDefaultConfiguration(
        RealmConfiguration.Builder()
            .name("favoriteMoviesDB.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
    )

}