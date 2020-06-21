package com.evlj.findmovie.data.sources.remote.mappers

import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.data.sources.remote.entities.RMovieDetail
import com.evlj.findmovie.domain.mappers.SingleMapper

class DMovieDetailMapper(
    private val genreMapper: DGenreMapper
) : SingleMapper<RMovieDetail, DMovieDetail>() {

    override fun transform(value: RMovieDetail): DMovieDetail {
        return DMovieDetail(
            id = value.id,
            budget = value.budget,
            _genres = value.genres.let(genreMapper::transform),
            homepage = value.homepage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            posterPath = value.posterPath,
            releaseDate = value.releaseDate,
            runtime = value.runtime,
            title = value.title,
            voteAverage = value.voteAverage
        )
    }
}