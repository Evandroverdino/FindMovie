package com.evlj.findmovie.model

class PMovieDetail(
    val id: Int,
    val _genres: List<PGenre>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val _runtime: Int,
    val title: String,
    val _voteAverage: Float,
    val isFavorite: Boolean
) {

    val genres: String
        get() = _genres.joinToString { it.name }

    val runtime: String
        get() = "$_runtime min"

    val voteAverage: String
        get() = _voteAverage.toString()
}