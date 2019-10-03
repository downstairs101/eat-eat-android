package com.downstairs.place.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PlaceDAO {

    @Query("SELECT * from PlaceEntity where id=:placeId")
    fun findById(placeId: String): PlaceEntity?

    @Query("SELECT * from PlaceEntity")
    fun findAll(): List<PlaceEntity>

    @Insert(onConflict = REPLACE)
    fun insertPlace(vararg placeEntity: PlaceEntity)
}