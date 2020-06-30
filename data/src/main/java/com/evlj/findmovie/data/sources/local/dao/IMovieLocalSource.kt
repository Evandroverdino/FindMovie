package com.evlj.findmovie.data.sources.local.dao

import androidx.room.*
import com.evlj.findmovie.data.entities.DMovieDetail

@Dao
interface IMovieLocalSource {

    @Query(
        value = """
            SELECT * FROM ${DMovieDetail.TABLE_NAME}
            WHERE ${DMovieDetail.FIELD_ID} = :movieId
        """
    )
    suspend fun searchMovie(movieId: Int): DMovieDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDetail: DMovieDetail)

    @Query(
        value = """
            DELETE FROM ${DMovieDetail.TABLE_NAME}
            WHERE ${DMovieDetail.FIELD_ID} = :movieId
        """
    )
    fun deleteMovie(movieId: Int)

    @Transaction
    suspend fun transaction(block: IMovieLocalSource.() -> Unit) {
        block()
    }
}