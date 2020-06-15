package com.evlj.findmovie.model

data class PDiscover(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<PMovie>
)