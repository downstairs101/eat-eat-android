package com.downstairs.tosplit.user.data

import com.squareup.moshi.Json

data class UserRemote(
    @Json(name = "name") val name: String
) {
    fun toDomain() = User(name)
}