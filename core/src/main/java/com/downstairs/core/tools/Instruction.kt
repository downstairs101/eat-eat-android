package com.downstairs.core.tools

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf

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

class Navigation(@IdRes val destination: Int) : Instruction() {

    private var arguments: Array<out Pair<String, Any?>> = arrayOf(Pair("", ""))

    val bundledArgs: Bundle
        get() = bundleOf(*arguments)

    fun putArguments(vararg arguments: Pair<String, Any?>) {
        this.arguments = arguments
    }
}

abstract class ViewInstruction {
    open fun success(): Instruction = State.Success
    open fun loading(): Instruction = State.Loading
    open fun failure(): Instruction = State.Failed(Failure.Undefined)
}