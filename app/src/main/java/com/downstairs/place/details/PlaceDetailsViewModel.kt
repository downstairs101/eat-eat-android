package com.downstairs.place.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.model.PlaceRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaceDetailsViewModel @Inject constructor(private val repository: PlaceRepository) : ViewModel() {

    init {
        insertPlace()
        loadPlace()
    }

    private val _editableState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also { it.value = false }
    }

    private val _name = MutableLiveData<String>()
    private val _category = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()

    val editableState: LiveData<Boolean> = _editableState

    val name: LiveData<String> = _name
    val category: LiveData<String> = _category
    val description: LiveData<String> = _description


    private fun loadPlace() {
        viewModelScope.launch {
            val place = repository.getPlace(1)
            place?.also {
                _name.value = it.name
                _category.value = it.category
                _description.value = it.description
            }
        }
    }

    fun enterOnEditMode() {
        _editableState.postValue(true)
    }

    private fun insertPlace() {
        viewModelScope.launch(IO) {
            repository.insert()
        }
    }


}