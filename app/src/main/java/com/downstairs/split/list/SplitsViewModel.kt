package com.downstairs.split.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.eatat.core.extensions.launchIO
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.split.Split
import com.downstairs.split.data.SplitUiModel
import javax.inject.Inject

class SplitsViewModel @Inject constructor(
    private val viewInstruction: SplitsViewInstruction,
    private val splitsInteractor: SplitsInteractor
) : ViewModel() {

    private val _viewInstruction = MutableLiveData<Instruction>()
    val viewState: LiveData<Instruction> = _viewInstruction

    private val mutableSplits = MutableLiveData<List<SplitUiModel>>()
    val splits: LiveData<List<SplitUiModel>> = mutableSplits

    init {
        viewModelScope.launchIO { loadSplits() }
    }

    private suspend fun loadSplits() {
        _viewInstruction.postValue(viewInstruction.loading())

        val result = splitsInteractor.fetchSpits(1)
        result.onSuccess { onSplitLoaded(it) }
        result.onFailure { onSplitError(it) }
    }

    private fun onSplitLoaded(splitList: List<Split>) {
        val uiSplits = splitList.map { SplitUiModel.fromDomain(it) }

        mutableSplits.postValue(uiSplits)
        _viewInstruction.postValue(viewInstruction.success())
    }

    private fun onSplitError(throwable: Throwable) {
        _viewInstruction.postValue(viewInstruction.failure())
    }

    fun onItemClick(splitUiModel: SplitUiModel) {
        val navigation = viewInstruction.navigateToSplitDetails(splitUiModel)
        _viewInstruction.postValue(navigation)
    }
}