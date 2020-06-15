package com.evlj.findmovie.domain.entities

data class Movie(
    val id: Int,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Float,
    val title: String,
    val popularity: Float,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String
)