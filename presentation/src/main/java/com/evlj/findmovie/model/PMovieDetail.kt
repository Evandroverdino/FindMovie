package com.evlj.findmovie.model

class PMovieDetail(
    val id: Int,
    val budget: Float,
    val _genres: List<PGenre>,
    val homepage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val _runtime: Int,
    val title: String,
    val _voteAverage: Float
) {

    val genres: String
        get() {
            val genresList = StringBuilder()
            _genres.forEachIndexed { index, genre ->
                genresList.append(genre.name)
                genresList.append(if (index != _genres.size - 1) ", " else "")
            }
            return genresList.toString()
        }

    val runtime: String
        get() = "$_runtime min"

    val voteAverage: String
        get() = _voteAverage.toString()
}