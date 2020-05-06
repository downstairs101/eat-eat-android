package com.downstairs.eatat.core.tools

import android.os.Bundle
import androidx.annotation.IdRes

sealed class Instruction

sealed class State : Instruction() {
    object Success : State()
    object Loading : State()
    data class Failed(val failure: Failure) : State()
}

sealed class Failure {
    object NoInternetConnection : Failure()
    object Undefined : Failure()
}

class Navigation(@IdRes val destination: Int, val arguments: Bundle? = null) : Instruction()

abstract class ViewInstruction {
    open fun success(): Instruction = State.Success
    open fun loading(): Instruction = State.Loading
    open fun failure(): Instruction = State.Failed(Failure.Undefined)
}