package com.evlj.findmovie.data.sources.remote.entities

import com.google.gson.annotations.SerializedName

data class RDiscover(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("results") val results: List<RMovie> = emptyList()
)