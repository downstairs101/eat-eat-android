package com.downstairs.tosplit.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.ViewInstruction
import com.downstairs.tosplit.user.UserComplianceInteractor
import com.downstairs.tosplit.user.UserComplianceResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject
constructor(
    private val instruction: HomeInstruction,
    private val userComplianceInteractor: UserComplianceInteractor
) : ViewModel() {

    init {
        checkUserCompliance()
    }

    private fun checkUserCompliance() {
        viewModelScope.launch {
            onComplianceCheckResult(userComplianceInteractor.checkUserCompliance())
        }
    }

    private fun onComplianceCheckResult(result: UserComplianceResult) {
        when (result) {
            is UserComplianceResult.Noncompliance -> print("User non compliance")
            else -> print("The user is fine :)")
        }
    }
}

class HomeInstruction @Inject constructor() : ViewInstruction() {
}