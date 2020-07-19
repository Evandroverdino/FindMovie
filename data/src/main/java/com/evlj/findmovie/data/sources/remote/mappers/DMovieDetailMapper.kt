package com.evlj.findmovie.data.sources.remote.mappers

import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.helpers.Constants.API_POSTER_SIZE_W342
import com.evlj.findmovie.data.helpers.Constants.API_POSTER_URL
import com.evlj.findmovie.data.sources.remote.entities.RMovieDetail
import com.evlj.findmovie.domain.mappers.SingleMapper

class DMovieDetailMapper(
    private val genreMapper: DGenreMapper
) : SingleMapper<RMovieDetail, DMovieDetail>() {

    override fun transform(value: RMovieDetail): DMovieDetail {
        return DMovieDetail(
            id = value.id,
            _genres = value.genres.let(genreMapper::transform),
            overview = value.overview,
            posterPath = value.posterPath.mapPosterPath(),
            releaseDate = value.releaseDate,
            runtime = value.runtime,
            title = value.title,
            voteAverage = value.voteAverage
        )
    }

    private fun String.mapPosterPath(): String {
        return API_POSTER_URL + API_POSTER_SIZE_W342 + this
    }
}