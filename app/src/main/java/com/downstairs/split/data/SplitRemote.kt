package com.downstairs.split.data

import com.downstairs.split.Split
import com.squareup.moshi.Json

data class SplitRemote(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "value") val value: Double,
    @Json(name = "splitters") val splitters: List<User>
) {

    fun toDomain(): Split {
        return Split(
            id = id,
            name = name,
            payer = User("Edgar"),
            value = value
        )
    }
}

data class User(@Json(name = "name") val name: String)