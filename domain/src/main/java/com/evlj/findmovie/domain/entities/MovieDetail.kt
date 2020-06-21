package com.evlj.findmovie.domain.entities

data class MovieDetail(
    val id: Int,
    val budget: Float,
    val genres: List<Genre>,
    val homepage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Float
)