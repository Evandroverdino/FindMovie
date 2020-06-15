package com.evlj.findmovie.data.sources.remote.mappers

import com.evlj.findmovie.data.entities.DDiscover
import com.evlj.findmovie.data.sources.remote.entities.RDiscover
import com.evlj.findmovie.domain.mappers.SingleMapper

class DDiscoverMapper(
    private val movieMapper: DMovieMapper
) : SingleMapper<RDiscover, DDiscover>() {

    override fun transform(value: RDiscover): DDiscover {
        return DDiscover(
            page = value.page,
            totalResults = value.totalResults,
            totalPages = value.totalPages,
            results = value.results.let(movieMapper::transform)
        )
    }
}