package com.evlj.findmovie.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.evlj.findmovie.data.entities.DMovieDetail.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DMovieDetail
@Ignore
constructor(
    @ColumnInfo(name = FIELD_ID) @PrimaryKey var id: Int,
    @ColumnInfo(name = FIELD_OVERVIEW) var overview: String,
    @ColumnInfo(name = FIELD_POSTER_PATH) var posterPath: String,
    @ColumnInfo(name = FIELD_RELEASE_DATE) var releaseDate: String,
    @ColumnInfo(name = FIELD_RUNTIME) var runtime: Int,
    @ColumnInfo(name = FIELD_TITLE) var title: String,
    @ColumnInfo(name = FIELD_VOTE_AVERAGE) var voteAverage: Float,
    @Ignore var _genres: List<DGenre> = emptyList()
) {
    var isFavorite: Boolean = true

    @Suppress("SuspiciousVarProperty")
    val genres: List<DGenre>
        get() = _genres.map { it.copy(movieId = id) }

    constructor() : this(0, "", "", "", 0, "", 0F)

    companion object {
        const val TABLE_NAME = "movie"

        const val FIELD_ID = "id"
        const val FIELD_OVERVIEW = "overview"
        const val FIELD_POSTER_PATH = "posterPath"
        const val FIELD_RELEASE_DATE = "releaseDate"
        const val FIELD_RUNTIME = "runtime"
        const val FIELD_TITLE = "title"
        const val FIELD_VOTE_AVERAGE = "voteAverage"
    }
}