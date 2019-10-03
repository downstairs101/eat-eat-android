package com.downstairs.place.data

import javax.inject.Inject

class PlaceRepository @Inject constructor(private val placeDAO: PlaceDAO) {

    fun getPlace(placeId: String) = placeDAO.findById(placeId)

    fun getAll() = placeDAO.findAll()

    fun insert(placeEntity: PlaceEntity) {
        placeDAO.insertPlace(placeEntity)
    }

}