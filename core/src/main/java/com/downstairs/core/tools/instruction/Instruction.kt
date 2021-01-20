package com.downstairs.core.tools.instruction

import androidx.navigation.NavDirections

open class Instruction

open class State : Instruction() {
    object Success : State()
    object Loading : State()
    data class Failed(val failure: Failure) : State()
}

class Direction(val direction: NavDirections) : Instruction()

sealed class Failure {
    object NoInternetConnection : Failure()
    object Undefined : Failure()
}

abstract class ViewInstruction {
    open fun success(): Instruction = State.Success
    open fun loading(): Instruction = State.Loading
    open fun failure(): Instruction = State.Failed(Failure.Undefined)
}