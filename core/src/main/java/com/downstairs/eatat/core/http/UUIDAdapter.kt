package com.downstairs.eatat.core.http

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class UUIDAdapter {

    @FromJson
    fun fromJson(id: String): UUID {
        return UUID.fromString(id)
    }

    @ToJson
    fun toJson(id: UUID) = id.toString()
}