package com.evlj.findmovie.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Genre(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = ""
) : RealmObject()