package com.evlj.findmovie.data.sources.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.evlj.findmovie.data.entities.DGenre
import io.reactivex.Single

@Dao
interface IGenreLocalSource {

    @Query(
        value = """
            SELECT * FROM ${DGenre.TABLE_NAME}
            WHERE ${DGenre.FIELD_MOVIE_ID} = :movieId
        """
    )
    fun searchGenres(movieId: Int): Single<List<DGenre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: DGenre)

    @Query(
        value = """
            DELETE FROM ${DGenre.TABLE_NAME}
            WHERE ${DGenre.FIELD_MOVIE_ID} = :movieId
        """
    )
    fun deleteGenres(movieId: Int)
}