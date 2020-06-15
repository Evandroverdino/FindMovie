package com.evlj.findmovie.mappers

import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.mappers.SingleMapper
import com.evlj.findmovie.model.PDiscover

class PDiscoverMapper(
    private val movieMapper: PMovieMapper
) : SingleMapper<Discover, PDiscover>() {

    override fun transform(value: Discover): PDiscover {
        return PDiscover(
            page = value.page,
            totalResults = value.totalResults,
            totalPages = value.totalPages,
            results = value.results.let(movieMapper::transform)
        )
    }
}