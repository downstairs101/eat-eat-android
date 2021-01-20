package com.downstairs.tosplit.split

import com.downstairs.tosplit.split.data.UserRemote

data class Split(
    val id: Long,
    val name: String,
    val payer: UserRemote,
    val value: Double
)