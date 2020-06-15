package com.evlj.findmovie.data.sources.remote.entities

import com.google.gson.annotations.SerializedName

data class RMovieDetail(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("budget") val budget: Float = 0.0F,
    @SerializedName("genres") val genres: List<RGenre> = emptyList(),
    @SerializedName("homepage") val homepage: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("poster_path") val posterPath: String = "",
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("vote_average") val voteAverage: Float = 0.0F
)