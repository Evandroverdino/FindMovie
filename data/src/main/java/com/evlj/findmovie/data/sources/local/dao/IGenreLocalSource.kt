package com.evlj.findmovie.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evlj.findmovie.data.entities.DGenre

@Dao
interface IGenreLocalSource {

    @Query(
        value = """
            SELECT * FROM ${DGenre.TABLE_NAME}
            WHERE ${DGenre.FIELD_MOVIE_ID} = :movieId
        """
    )
    suspend fun searchGenres(movieId: Int): List<DGenre>

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