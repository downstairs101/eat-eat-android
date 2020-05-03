package com.downstairs.split.data

import com.squareup.moshi.Json

data class SplitRemote(
    @Json(name = "name") val name: String,
    @Json(name = "value") val value: Double,
    @Json(name = "splitters") val splitters: List<User>
)

data class User(@Json(name = "name") val name: String)