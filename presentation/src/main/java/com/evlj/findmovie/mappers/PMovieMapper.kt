package com.evlj.findmovie.mappers

import com.evlj.findmovie.domain.entities.Movie
import com.evlj.findmovie.domain.mappers.SingleMapper
import com.evlj.findmovie.model.PMovie

class PMovieMapper : SingleMapper<Movie, PMovie>() {

    override fun transform(value: Movie): PMovie {
        return PMovie(
            id = value.id,
            voteCount = value.voteCount,
            video = value.video,
            voteAverage = value.voteAverage,
            title = value.title,
            popularity = value.popularity,
            posterPath = value.posterPath,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            genreIds = value.genreIds,
            backdropPath = value.backdropPath,
            adult = value.adult,
            overview = value.overview,
            releaseDate = value.releaseDate
        )
    }
}