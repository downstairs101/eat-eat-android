package com.downstairs.tosplit.split.data

import com.squareup.moshi.Json

data class UserRemote(@Json(name = "name") val name: String)