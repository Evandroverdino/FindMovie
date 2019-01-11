package com.evlj.findmovie.model

import com.google.gson.annotations.SerializedName

class MovieDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("budget") val budget: Float,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Float
) {

    fun getGenres(): String {
        val genresList = StringBuilder()
        genres.forEachIndexed { index, genre ->
            genresList.append(genre.name)
            genresList.append(if (index != genres.size - 1) ", " else "")
        }
        return genresList.toString()
    }

    fun getRuntime(): String = runtime.toString() + " min"

    fun getVoteAverage(): String = voteAverage.toString()

}