package com.evlj.findmovie.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evlj.findmovie.data.entities.DGenre
import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.local.dao.IGenreLocalSource
import com.evlj.findmovie.data.sources.local.dao.IMovieLocalSource

@Database(
    entities = [
        DMovieDetail::class,
        DGenre::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseSource : RoomDatabase() {

    abstract fun movieLocalSource(): IMovieLocalSource
    abstract fun genreLocalSource(): IGenreLocalSource

    companion object {
        const val DB_NAME = "favorites.bin"
    }
}