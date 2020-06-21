package com.evlj.findmovie.data.sources.remote.mappers

import com.evlj.findmovie.data.entities.DGenre
import com.evlj.findmovie.data.sources.remote.entities.RGenre
import com.evlj.findmovie.domain.mappers.SingleMapper

class DGenreMapper : SingleMapper<RGenre, DGenre>() {

    override fun transform(value: RGenre): DGenre {
        return DGenre(
            id = value.id,
            movieId = 0,
            name = value.name
        )
    }
}