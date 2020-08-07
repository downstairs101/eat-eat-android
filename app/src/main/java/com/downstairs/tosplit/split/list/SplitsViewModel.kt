package com.downstairs.tosplit.split.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.tools.Instruction
import com.downstairs.core.tools.SingleLiveEvent
import com.downstairs.tosplit.split.Split
import com.downstairs.tosplit.split.data.SplitUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplitsViewModel @Inject constructor(
    private val viewInstruction: SplitsViewInstruction,
    private val splitsInteractor: SplitsInteractor
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewState: LiveData<Instruction> = _viewInstruction

    private val mutableSplits = MutableLiveData<List<SplitUiModel>>()
    val splits: LiveData<List<SplitUiModel>> = mutableSplits

    fun loadSplits() {
        _viewInstruction.postValue(viewInstruction.loading())

        viewModelScope.launch {
            val result = splitsInteractor.fetchSpits()
            result.onSuccess { onSplitLoaded(it) }
            result.onFailure { onSplitError(it) }
        }
    }

    fun onItemClick(splitUiModel: SplitUiModel) {
        val navigation = viewInstruction.navigateToSplitDetails(splitUiModel)
        _viewInstruction.postValue(navigation)
    }

    private fun onSplitLoaded(splitList: List<Split>) {
        val uiSplits = splitList.map { SplitUiModel.fromDomain(it) }

        mutableSplits.postValue(uiSplits)
        _viewInstruction.postValue(viewInstruction.success())
    }

    private fun onSplitError(throwable: Throwable) {
        _viewInstruction.postValue(viewInstruction.failure())
    }
}