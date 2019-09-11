package com.downstairs.place.model

import javax.inject.Inject

class PlaceRepository @Inject constructor(private val placeDAO: PlaceDAO) {

    fun getPlace(placeId: Long) = placeDAO.findById(placeId)

    fun getAll() = placeDAO.findAll()

    fun insert(place: Place) {
        placeDAO.insertPlace(place)
    }

}