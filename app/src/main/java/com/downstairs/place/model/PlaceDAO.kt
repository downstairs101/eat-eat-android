package com.downstairs.place.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PlaceDAO {

    @Query("SELECT * from Place where id = :placeId")
    fun findById(placeId: Int): Place

    @Insert(onConflict = REPLACE)
    fun insertPlace(vararg place: Place)
}