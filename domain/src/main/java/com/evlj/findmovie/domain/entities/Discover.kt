package com.evlj.findmovie.domain.entities

data class Discover(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<Movie>
)