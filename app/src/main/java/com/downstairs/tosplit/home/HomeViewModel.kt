package com.downstairs.tosplit.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.tools.SingleLiveEvent
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.core.tools.instruction.ViewInstruction
import com.downstairs.tosplit.MainNavigationGraphDirections
import com.downstairs.tosplit.user.compliance.UserComplianceInteractor
import com.downstairs.tosplit.user.compliance.UserComplianceResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject
constructor(
    private val instruction: HomeInstruction,
    private val userComplianceInteractor: UserComplianceInteractor
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    init {
        checkUserCompliance()
    }

    private fun checkUserCompliance() {
        viewModelScope.launch {
            onComplianceCheckResult(userComplianceInteractor.checkUserCompliance())
        }
    }

    private fun onComplianceCheckResult(result: UserComplianceResult) {
        if (result is UserComplianceResult.Noncompliance) {
            _viewInstruction.postValue(instruction.navigateToUserRegister())
        }
    }
}

class HomeInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToUserRegister() = Direction(MainNavigationGraphDirections.toUserRegister())
}