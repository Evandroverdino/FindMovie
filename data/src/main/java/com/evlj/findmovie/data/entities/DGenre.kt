package com.evlj.findmovie.data.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DGenre(
    @PrimaryKey
    val id: Int,
    val name: String
) : RealmObject()