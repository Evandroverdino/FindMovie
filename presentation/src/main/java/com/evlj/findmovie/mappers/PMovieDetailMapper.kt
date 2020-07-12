package com.evlj.findmovie.mappers

import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.mappers.DualMapper
import com.evlj.findmovie.model.PMovieDetail

class PMovieDetailMapper(
    private val genresMapper: PGenreMapper
) : DualMapper<MovieDetail, PMovieDetail>() {

    override fun transform(value: MovieDetail): PMovieDetail {
        return PMovieDetail(
            id = value.id,
            budget = value.budget,
            _genres = value.genres.let(genresMapper::transform),
            homepage = value.homepage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            posterPath = value.posterPath,
            releaseDate = value.releaseDate,
            _runtime = value.runtime,
            title = value.title,
            _voteAverage = value.voteAverage
        )
    }

    override fun parseBack(value: PMovieDetail): MovieDetail {
        return MovieDetail(
            id = value.id,
            budget = value.budget,
            genres = value._genres.let(genresMapper::parseBack),
            homepage = value.homepage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            posterPath = value.posterPath,
            releaseDate = value.releaseDate,
            runtime = value._runtime,
            title = value.title,
            voteAverage = value._voteAverage
        )
    }
}