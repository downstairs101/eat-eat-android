package com.downstairs.tosplit.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.auth.AuthInteractor
import com.downstairs.core.auth.method.AuthMethod
import com.downstairs.core.auth.AuthResult
import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.extensions.launchIO
import com.downstairs.core.tools.Instruction
import com.downstairs.core.tools.Direction
import com.downstairs.core.tools.ViewInstruction
import com.downstairs.tosplit.R
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val instruction: LoginViewInstruction,
    private val authInteractor: AuthInteractor
) : ViewModel() {

    private val _viewInstruction = MutableLiveData<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    fun signIn(authMethod: AuthMethod) {
        viewModelScope.launchIO {
            onAuthResult(authInteractor.authorize(authMethod))
        }
    }

    private fun onAuthResult(result: AuthResult) {
        if (result is AuthResult.Authorized) {
            _viewInstruction.postValue(instruction.navigateToHome())
        } else {
            _viewInstruction.postValue(instruction.failure())
        }
    }

    fun processSignInResult(authResultData: AuthResultData) {
        authInteractor.processAuthResult(authResultData)
    }
}

class LoginViewInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToHome() = Direction(R.id.fromLoginToHome)
}