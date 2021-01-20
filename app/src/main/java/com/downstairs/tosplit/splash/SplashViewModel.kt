package com.downstairs.tosplit.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.downstairs.core.auth.AuthInteractor
import com.downstairs.core.auth.AuthResult
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.core.tools.SingleLiveEvent
import com.downstairs.core.tools.instruction.ViewInstruction
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val instruction: SplashInstruction
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    fun checkUserStatus() {
        when (authInteractor.checkAuthStatus()) {
            is AuthResult.Authorized -> _viewInstruction.postValue(instruction.navigateToHome())
            is AuthResult.Unauthorized -> _viewInstruction.postValue(instruction.navigateToLogin())
        }
    }
}

class SplashInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToHome() = Direction(SplashFragmentDirections.fromSpashToHome())

    fun navigateToLogin() = Direction(SplashFragmentDirections.fromSpashToLogin())
}