package com.downstairs.tosplit.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.downstairs.core.auth.AuthInteractor
import com.downstairs.core.auth.AuthResult
import com.downstairs.core.tools.Direction
import com.downstairs.core.tools.Instruction
import com.downstairs.core.tools.SingleLiveEvent
import com.downstairs.core.tools.ViewInstruction
import com.downstairs.tosplit.R
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val instruction: SplashInstruction
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    fun checkUserStatus() {
        when (authInteractor.checkAuthStatus()) {
            is AuthResult.Authorized -> _viewInstruction.postValue(instruction.homeDirection())
            is AuthResult.Unauthorized -> _viewInstruction.postValue(instruction.loginDirection())
        }
    }
}

class SplashInstruction @Inject constructor() : ViewInstruction() {

    fun homeDirection() = Direction(R.id.fromSpashToHome)

    fun loginDirection() = Direction(R.id.fromSpashToLogin)
}