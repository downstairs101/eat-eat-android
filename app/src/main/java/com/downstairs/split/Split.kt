package com.downstairs.split

import com.downstairs.split.data.User

data class Split(val name: String, val payer: User, val value: Double)