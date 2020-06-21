package com.evlj.findmovie.data.sources.local.dao

import android.arch.persistence.room.*
import com.evlj.findmovie.data.entities.DMovieDetail
import io.reactivex.Single

@Dao
interface IMovieLocalSource {

    @Query(
        value = """
            SELECT * FROM ${DMovieDetail.TABLE_NAME}
            WHERE ${DMovieDetail.FIELD_ID} = :movieId
        """
    )
    fun searchMovie(movieId: Int): Single<DMovieDetail>

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
    fun transaction(block: IMovieLocalSource.() -> Unit) {
        block()
    }
}