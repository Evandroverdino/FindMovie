package com.evlj.findmovie.model

data class PMovie(
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