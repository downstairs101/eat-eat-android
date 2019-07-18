package com.downstairs.place.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.model.Place
import com.downstairs.place.model.PlaceRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaceDetailsViewModel @Inject constructor(private val repository: PlaceRepository) : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _category = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _editableState = MutableLiveData<Boolean>()

    val name: LiveData<String> = _name
    val category: LiveData<String> = _category
    val description: LiveData<String> = _description
    val editableState: LiveData<Boolean> = _editableState

    fun fetchPlace(placeId: Int?) {
        if (placeId == null) {
            enterOnEditMode()
        } else {
            loadPlace(placeId)
        }
    }

    fun enterOnEditMode() {
        _editableState.postValue(true)
    }

    private fun loadPlace(placeId: Int) {
        viewModelScope.launch {
            val place = repository.getPlace(placeId)
            place?.also { bindPlace(it) }
        }
    }

    private fun bindPlace(it: Place) {
        _name.value = it.name
        _category.value = it.category
        _description.value = it.description
    }

    private fun insertPlace() {
        viewModelScope.launch(IO) {
            repository.insert()
        }
    }
}