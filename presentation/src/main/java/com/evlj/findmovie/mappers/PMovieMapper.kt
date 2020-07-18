package com.evlj.findmovie.mappers

import com.evlj.findmovie.domain.entities.Movie
import com.evlj.findmovie.domain.mappers.SingleMapper
import com.evlj.findmovie.model.PMovie

class PMovieMapper : SingleMapper<Movie, PMovie>() {

    override fun transform(value: Movie): PMovie {
        return PMovie(
            id = value.id,
            title = value.title,
            posterPath = value.posterPath,
            overview = value.overview
        )
    }
}