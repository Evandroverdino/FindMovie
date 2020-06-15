package com.evlj.findmovie.data.mappers

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.domain.entities.Discover
import com.evlj.findmovie.domain.mappers.SingleMapper

class DiscoverMapper(
    private val movieMapper: MovieMapper
) : SingleMapper<DDiscover, Discover>() {

    override fun transform(value: DDiscover): Discover {
        return Discover(
            page = value.page,
            totalResults = value.totalResults,
            totalPages = value.totalPages,
            results = value.results.let(movieMapper::transform)
        )
    }
}