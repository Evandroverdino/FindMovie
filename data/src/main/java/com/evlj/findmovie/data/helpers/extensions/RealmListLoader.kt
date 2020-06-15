package com.evlj.findmovie.data.helpers.extensions

import com.evlj.findmovie.domain.mappers.SingleMapper
import io.realm.RealmList

fun <T, R> List<T>.transformToRealmList(mapper: SingleMapper<T, R>): RealmList<R> =
    RealmList<R>().apply { addAll(this@transformToRealmList.let(mapper::transform)) }