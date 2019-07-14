package com.downstairs.place.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlaceDAO {
    @Query("SELECT * from place where id = :placeId")
    suspend fun findById(placeId: Int): Place
}