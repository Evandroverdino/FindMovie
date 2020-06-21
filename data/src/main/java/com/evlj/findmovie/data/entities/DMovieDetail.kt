package com.evlj.findmovie.data.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.evlj.findmovie.data.entities.DMovieDetail.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DMovieDetail
@Ignore
constructor(
    @ColumnInfo(name = FIELD_ID) @PrimaryKey var id: Int,
    @ColumnInfo(name = FIELD_BUDGET) var budget: Float,
    @ColumnInfo(name = FIELD_HOMEPAGE) var homepage: String,
    @ColumnInfo(name = FIELD_ORIGINAL_TITLE) var originalTitle: String,
    @ColumnInfo(name = FIELD_OVERVIEW) var overview: String,
    @ColumnInfo(name = FIELD_POSTER_PATH) var posterPath: String,
    @ColumnInfo(name = FIELD_RELEASE_DATE) var releaseDate: String,
    @ColumnInfo(name = FIELD_RUNTIME) var runtime: Int,
    @ColumnInfo(name = FIELD_TITLE) var title: String,
    @ColumnInfo(name = FIELD_VOTE_AVERAGE) var voteAverage: Float,
    @Ignore var _genres: List<DGenre> = emptyList()
) {

    @Suppress("SuspiciousVarProperty")
    val genres: List<DGenre>
        get() = _genres.map { it.copy(movieId = id) }

    constructor() : this(
        0, 0F, "",
        "", "", "",
        "", 0, "", 0F
    )

    companion object {
        const val TABLE_NAME = "movie"

        const val FIELD_ID = "id"
        const val FIELD_BUDGET = "budget"
        const val FIELD_HOMEPAGE = "homepage"
        const val FIELD_ORIGINAL_TITLE = "originalTitle"
        const val FIELD_OVERVIEW = "overview"
        const val FIELD_POSTER_PATH = "posterPath"
        const val FIELD_RELEASE_DATE = "releaseDate"
        const val FIELD_RUNTIME = "runtime"
        const val FIELD_TITLE = "title"
        const val FIELD_VOTE_AVERAGE = "voteAverage"
    }
}