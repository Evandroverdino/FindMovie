package com.evlj.findmovie.data.mappers

import com.evlj.findmovie.data.entities.DMovie
import com.evlj.findmovie.domain.entities.Movie
import com.evlj.findmovie.domain.mappers.SingleMapper

class MovieMapper : SingleMapper<DMovie, Movie>() {

    override fun transform(value: DMovie): Movie {
        return Movie(
            id = value.id,
            title = value.title,
            posterPath = value.posterPath,
            overview = value.overview
        )
    }
}