package com.evlj.findmovie.model

class PMovieDetail(
    val id: Int,
    val budget: Float,
    val genres: List<PGenre>,
    val homepage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Float,
    var isFavorite: Boolean = false
) {

    fun getGenres(): String {
        val genresList = StringBuilder()
        genres.forEachIndexed { index, genre ->
            genresList.append(genre.name)
            genresList.append(if (index != genres.size - 1) ", " else "")
        }
        return genresList.toString()
    }

    fun getRuntime(): String = "$runtime min"

    fun getVoteAverage(): String = voteAverage.toString()
}