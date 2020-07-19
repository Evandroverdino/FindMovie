package com.evlj.findmovie.domain.entities

data class MovieDetail(
    val id: Int,
    val genres: List<Genre>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Float,
    val isFavorite: Boolean
)