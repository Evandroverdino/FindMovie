package com.evlj.findmovie.data.sources.remote.mappers

import com.evlj.findmovie.data.helpers.Constants.API_POSTER_SIZE_W185
import com.evlj.findmovie.data.helpers.Constants.API_POSTER_URL
import com.evlj.findmovie.data.entities.DMovie
import com.evlj.findmovie.data.sources.remote.entities.RMovie
import com.evlj.findmovie.domain.mappers.SingleMapper

class DMovieMapper : SingleMapper<RMovie, DMovie>() {

    override fun transform(value: RMovie): DMovie {
        return DMovie(
            id = value.id,
            title = value.title,
            posterPath = value.posterPath.mapPosterPath(),
            overview = value.overview
        )
    }

    private fun String.mapPosterPath(): String {
        return API_POSTER_URL + API_POSTER_SIZE_W185 + this
    }
}