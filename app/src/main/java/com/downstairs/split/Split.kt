package com.downstairs.split

import com.downstairs.split.data.UserRemote

data class Split(
    val id: Int,
    val name: String,
    val payer: UserRemote,
    val value: Double
)