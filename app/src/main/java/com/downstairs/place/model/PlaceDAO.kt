package com.downstairs.place.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.Deferred

@Dao
interface PlaceDAO {

    @Query("SELECT * from place where id = :placeId")
    suspend fun findById(placeId: Int): Deferred<Place>

    @Insert
    suspend fun insertPlace(place: Place)
}