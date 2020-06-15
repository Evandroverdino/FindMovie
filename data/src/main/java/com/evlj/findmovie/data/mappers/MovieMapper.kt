package com.evlj.findmovie.data.mappers

import com.evlj.findmovie.data.entities.DMovie
import com.evlj.findmovie.domain.entities.Movie
import com.evlj.findmovie.domain.mappers.SingleMapper

class MovieMapper : SingleMapper<DMovie, Movie>() {

    override fun transform(value: DMovie): Movie {
        return Movie(
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