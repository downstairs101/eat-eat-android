package com.downstairs.split

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplitsViewModel @Inject constructor() : ViewModel() {

    private val mutableSplits = MutableLiveData<List<SplitUiModel>>()
    val splits: LiveData<List<SplitUiModel>> = mutableSplits
}