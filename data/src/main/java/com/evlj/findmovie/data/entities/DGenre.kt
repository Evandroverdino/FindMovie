package com.evlj.findmovie.data.entities

import androidx.room.*
import com.evlj.findmovie.data.entities.DGenre.Companion.FIELD_MOVIE_ID
import com.evlj.findmovie.data.entities.DGenre.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = DMovieDetail::class,
            parentColumns = arrayOf(DMovieDetail.FIELD_ID),
            childColumns = arrayOf(FIELD_MOVIE_ID),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(FIELD_MOVIE_ID)]
)
data class DGenre
@Ignore
constructor(
    @ColumnInfo(name = FIELD_ID) @PrimaryKey var id: Int,
    @ColumnInfo(name = FIELD_MOVIE_ID) var movieId: Int,
    @ColumnInfo(name = FIELD_NAME) var name: String
) {

    constructor() : this(0, 0, "")

    companion object {
        const val TABLE_NAME = "genre"

        const val FIELD_ID = "id"
        const val FIELD_MOVIE_ID = "movieId"
        const val FIELD_NAME = "name"
    }
}