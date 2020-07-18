package com.evlj.findmovie.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String
)