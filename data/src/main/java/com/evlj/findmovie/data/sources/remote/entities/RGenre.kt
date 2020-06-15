package com.evlj.findmovie.data.sources.remote.entities

import com.google.gson.annotations.SerializedName

data class RGenre(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = ""
)