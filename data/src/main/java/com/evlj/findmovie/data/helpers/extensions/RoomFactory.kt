package com.evlj.findmovie.data.helpers.extensions

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

inline fun <reified T : RoomDatabase> createRoomDb(context: Context, dbName: String): T {
    return Room
        .databaseBuilder(context.applicationContext, T::class.java, dbName)
        .fallbackToDestructiveMigration()
        .build()
}