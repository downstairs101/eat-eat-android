package com.downstairs.place.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PlaceDetailsViewModel @Inject constructor() : ViewModel() {

    private val _editableState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also { it.value = false }
    }

    val editableState: LiveData<Boolean> = _editableState

    fun enterOnEditMode() {
        _editableState.postValue(true)
    }


}