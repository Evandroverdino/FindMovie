package com.evlj.findmovie.data.mappers

import com.evlj.findmovie.data.entities.DMovieDetail
import com.evlj.findmovie.domain.entities.MovieDetail
import com.evlj.findmovie.domain.mappers.DualMapper

class MovieDetailsMapper(
    private val genreMapper: GenreMapper
) : DualMapper<DMovieDetail, MovieDetail>() {

    override fun transform(value: DMovieDetail): MovieDetail {
        return MovieDetail(
            id = value.id,
            genres = value.genres.let(genreMapper::transform),
            overview = value.overview,
            posterPath = value.posterPath,
            releaseDate = value.releaseDate,
            runtime = value.runtime,
            title = value.title,
            voteAverage = value.voteAverage,
            isFavorite = value.isFavorite
        )
    }

    override fun parseBack(value: MovieDetail): DMovieDetail {
        return DMovieDetail(
            id = value.id,
            overview = value.overview,
            _genres = value.genres.let(genreMapper::parseBack),
            posterPath = value.posterPath,
            releaseDate = value.releaseDate,
            runtime = value.runtime,
            title = value.title,
            voteAverage = value.voteAverage
        )
    }
}