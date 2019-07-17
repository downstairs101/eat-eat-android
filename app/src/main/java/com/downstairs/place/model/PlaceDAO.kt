package com.downstairs.place.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PlaceDAO {

    @Query("SELECT * from Place where id=:placeId")
    suspend fun findById(placeId: Int): Place?

    @Query("SELECT * from Place")
    suspend fun findAll(): List<Place>

    @Insert(onConflict = REPLACE)
    suspend fun insertPlace(vararg place: Place)
}