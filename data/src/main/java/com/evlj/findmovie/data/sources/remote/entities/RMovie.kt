package com.evlj.findmovie.data.sources.remote.entities

import com.google.gson.annotations.SerializedName

data class RMovie(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("vote_count") val voteCount: Int = 0,
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("vote_average") val voteAverage: Float = 0.0F,
    @SerializedName("title") val title: String = "",
    @SerializedName("popularity") val popularity: Float = 0.0F,
    @SerializedName("poster_path") val posterPath: String = "",
    @SerializedName("original_language") val originalLanguage: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("backdrop_path") val backdropPath: String = "",
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("overview") val overview: String = "",
    @SerializedName("release_date") val releaseDate: String = ""
)