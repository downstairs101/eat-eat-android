package com.downstairs.eatat.core.tools

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

class Navigation constructor(@IdRes val destination: Int) : Instruction() {

    var arguments: Array<out Pair<String, Any?>> = arrayOf(Pair("", ""))
        private set

    fun putArguments(vararg arguments: Pair<String, Any?>) {
        this.arguments = arguments
    }
}

abstract class ViewInstruction {
    open fun success(): Instruction = State.Success
    open fun loading(): Instruction = State.Loading
    open fun failure(): Instruction = State.Failed(Failure.Undefined)
}