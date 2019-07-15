package com.downstairs.place.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val category: String,
    @ColumnInfo val description: String
)