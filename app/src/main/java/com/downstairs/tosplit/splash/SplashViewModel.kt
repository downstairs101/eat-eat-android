package com.downstairs.tosplit.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.tools.SingleLiveEvent
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.core.tools.instruction.ViewInstruction
import com.downstairs.tosplit.login.AuthResult
import com.downstairs.tosplit.login.LoginInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val instruction: SplashInstruction
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    fun checkUserStatus() {
        viewModelScope.launch {
            onUserStatusResult(loginInteractor.check())
        }
    }

    private fun onUserStatusResult(result: AuthResult) {
        when (result) {
            is AuthResult.Unauthorized -> _viewInstruction.postValue(instruction.navigateToLogin())
            is AuthResult.Noncompliance -> _viewInstruction.postValue(instruction.navigateToUserRegister())
            is AuthResult.Compliance -> _viewInstruction.postValue(instruction.navigateToHome())
        }
    }
}

class SplashInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToLogin() = Direction(SplashFragmentDirections.fromSpashToLogin())

    fun navigateToUserRegister() = Direction(SplashFragmentDirections.toUserRegister())

    fun navigateToHome() = Direction(SplashFragmentDirections.fromSpashToHome())
}