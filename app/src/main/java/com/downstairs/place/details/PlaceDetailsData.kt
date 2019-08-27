package com.downstairs.place.details

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class PlaceDetailsData(
    _id: Long? = -1,
    _name: String,
    _category: String,
    _description: String
) : BaseObservable() {

    @get:Bindable
    val id = _id

    @get:Bindable
    val name = _name

    @get:Bindable
    val category = _category

    @get:Bindable
    val description = _description

}