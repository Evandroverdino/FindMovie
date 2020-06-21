package com.evlj.findmovie.mappers

import com.evlj.findmovie.domain.entities.Genre
import com.evlj.findmovie.domain.mappers.DualMapper
import com.evlj.findmovie.model.PGenre

class PGenreMapper : DualMapper<Genre, PGenre>() {

    override fun transform(value: Genre): PGenre {
        return PGenre(
            id = value.id,
            movieId = value.movieId,
            name = value.name
        )
    }

    override fun parseBack(value: PGenre): Genre {
        return Genre(
            id = value.id,
            movieId = value.movieId,
            name = value.name
        )
    }
}