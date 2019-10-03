package com.downstairs.place.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PlaceEntity(
    @PrimaryKey
    @ColumnInfo val id: String = UUID.randomUUID().toString(),
    @ColumnInfo val name: String,
    @ColumnInfo val category: String,
    @ColumnInfo val description: String
)