package com.downstairs.tosplit.split.data

import com.downstairs.tosplit.split.Split
import com.squareup.moshi.Json

data class SplitRemote(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "value") val value: Double,
    @Json(name = "splitters") val splitters: List<UserRemote>
) {

    fun toDomain(): Split {
        return Split(
            id = id,
            name = name,
            payer = UserRemote("Edgar"),
            value = value
        )
    }
}

