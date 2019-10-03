package com.downstairs.place.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PlaceDAO {

    @Query("SELECT * from Place where id=:placeId")
    fun findById(placeId: Long): Place?

    @Query("SELECT * from Place")
    fun findAll(): List<Place>

    @Insert(onConflict = REPLACE)
    fun insertPlace(vararg place: Place)
}