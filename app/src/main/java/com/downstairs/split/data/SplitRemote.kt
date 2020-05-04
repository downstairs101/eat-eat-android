package com.downstairs.split.data

import com.downstairs.split.Split
import com.squareup.moshi.Json

data class SplitRemote(
    @Json(name = "name") val name: String,
    @Json(name = "value") val value: Double,
    @Json(name = "splitters") val splitters: List<User>
) {
    fun toDomain(): Split {
        return Split(name = name, payer = User("Edgar"), value = value)
    }
}

data class User(@Json(name = "name") val name: String)