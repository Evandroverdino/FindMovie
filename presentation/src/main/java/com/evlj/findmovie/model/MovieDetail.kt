package com.evlj.findmovie.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieDetail(
    @PrimaryKey
    @SerializedName("id") var id: Int = 0,
    @SerializedName("budget") var budget: Float = 0.0F,
    @SerializedName("genres") var genres: RealmList<Genre> = RealmList(),
    @SerializedName("homepage") var homepage: String = "",
    @SerializedName("original_title") var originalTitle: String = "",
    @SerializedName("overview") var overview: String = "",
    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    @SerializedName("runtime") var runtime: Int = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("vote_average") var voteAverage: Float = 0.0F,
    var isFavorite: Boolean = false
) : RealmObject() {

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