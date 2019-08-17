package com.downstairs.place.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.model.PlaceRepository
import kotlinx.coroutines.async
import javax.inject.Inject

class PlaceListVIewModel @Inject constructor(private val repository: PlaceRepository) :
    ViewModel() {

    private val _places by lazy { MutableLiveData<List<PlaceListItem>>().also { loadPlaces() } }

    fun places(): LiveData<List<PlaceListItem>> = _places

    private fun loadPlaces() {
        viewModelScope.async {
            val places = repository.getAll()

            val placeListItems = places.map {
                PlaceListItem(it.id, it.name, it.category, it.description)
            }

            _places.postValue(placeListItems)
        }
    }

}