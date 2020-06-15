package com.evlj.findmovie.domain.mappers

abstract class SingleMapper<FROM, TO> {

    abstract fun transform(value: FROM): TO

    fun transform(value: List<FROM>): List<TO> = value.map(::transform)
}