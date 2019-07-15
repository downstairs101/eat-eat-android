package com.downstairs.place.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.model.Place
import com.downstairs.place.model.PlaceRepository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

class PlaceDetailsViewModel(private val repository: PlaceRepository) : ViewModel() {


    private val _editableState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also { it.value = false }
    }

    private val _placeData = MutableLiveData<Place>()

    private fun fetchPlace(placeId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            _placeData.value = repository.get(0).await()
        }
    }

    val editableState: LiveData<Boolean> = _editableState

    fun enterOnEditMode() {
        _editableState.postValue(true)
    }


}