package com.evlj.findmovie.data.entities

data class DDiscover(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<DMovie>
)