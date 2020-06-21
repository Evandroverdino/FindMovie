package com.evlj.findmovie.data.mappers

import com.evlj.findmovie.data.entities.DGenre
import com.evlj.findmovie.domain.entities.Genre
import com.evlj.findmovie.domain.mappers.DualMapper

class GenreMapper : DualMapper<DGenre, Genre>() {

    override fun transform(value: DGenre): Genre {
        return Genre(
            id = value.id,
            movieId = value.movieId,
            name = value.name
        )
    }

    override fun parseBack(value: Genre): DGenre {
        return DGenre(
            id = value.id,
            movieId = value.movieId,
            name = value.name
        )
    }
}