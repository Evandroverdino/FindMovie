package com.evlj.findmovie.data.sources.remote.mappers

import com.evlj.findmovie.data.entities.DMovie
import com.evlj.findmovie.data.sources.remote.entities.RMovie
import com.evlj.findmovie.domain.mappers.SingleMapper

class DMovieMapper : SingleMapper<RMovie, DMovie>() {

    override fun transform(value: RMovie): DMovie {
        return DMovie(
            voteCount = value.voteCount,
            id = value.id,
            video = value.video,
            voteAverage = value.voteAverage,
            title = value.title,
            popularity = value.popularity,
            posterPath = value.posterPath,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            genreIds = value.genreIds,
            backdropPath = value.backdropPath ?: "",
            adult = value.adult,
            overview = value.overview,
            releaseDate = value.releaseDate
        )
    }
}