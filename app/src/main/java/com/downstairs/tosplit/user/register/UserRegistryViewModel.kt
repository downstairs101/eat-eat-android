package com.downstairs.tosplit.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.core.tools.instruction.ViewInstruction
import com.downstairs.tosplit.user.data.UserUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRegistryViewModel @Inject
constructor(
    private val instruction: UserRegistryViewInstruction,
    private val saveUserInteractor: SaveUserInteractor
) : ViewModel() {

    private val _viewInstruction = MutableLiveData<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction


    fun saveUser(userUI: UserUiModel) {
        viewModelScope.launch {
            _viewInstruction.postValue(instruction.loading())
            val result = saveUserInteractor.save(userUI.toDomain())

            result.onComplete { _viewInstruction.postValue(instruction.navigateToHome()) }
            result.onError { }
        }
    }
}

class UserRegistryViewInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToHome() =
        Direction(UserRegistryFragmentDirections.fromUserRegisterToHomeFragment())
}