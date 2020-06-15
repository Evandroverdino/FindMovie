package com.evlj.findmovie.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DMovieDetail(
    @PrimaryKey
    val id: Int,
    val budget: Float,
    val genres: RealmList<DGenre> = RealmList(),
    val homepage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Float,
    var isFavorite: Boolean = false
) : RealmObject()