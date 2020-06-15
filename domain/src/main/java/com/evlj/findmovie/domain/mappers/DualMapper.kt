package com.evlj.findmovie.domain.mappers

abstract class DualMapper<FROM, TO> {

    abstract fun transform(value: FROM): TO

    abstract  fun parseBack(value: TO): FROM

    fun transform(value: List<FROM>): List<TO> = value.map(::transform)

    fun parseBack(value: List<TO>): List<FROM> = value.map(::parseBack)
}