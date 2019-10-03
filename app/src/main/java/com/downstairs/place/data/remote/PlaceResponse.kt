package com.downstairs.place.data.remote

import com.squareup.moshi.Json
import java.util.*

data class PlaceResponse(
    @field:Json(name = "id") val id: UUID,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "category") val category: String,
    @field:Json(name = "description") val description: String
)