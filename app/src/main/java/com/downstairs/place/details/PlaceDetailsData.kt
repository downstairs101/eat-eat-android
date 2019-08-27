package com.downstairs.place.details

import androidx.databinding.ObservableField

data class PlaceDetailsData(
    val id: Long,
    val name: ObservableField<String>,
    val category: ObservableField<String>,
    val description: ObservableField<String>
)