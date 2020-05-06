package com.downstairs.eatat.core.tools

import android.os.Bundle
import androidx.annotation.IdRes

sealed class Action

sealed class State : Action() {
    object Success : State()
    object Loading : State()
    data class Failed(val failure: Failure) : State()
}

sealed class Failure {
    object NoInternetConnection : Failure()
    object Undefined : Failure()
}

class Navigation(@IdRes val destination: Int, val arguments: Bundle? = null) : Action()

abstract class ViewAction {
    open fun success(): Action = State.Success
    open fun loading(): Action = State.Loading
    open fun failure(): Action = State.Failed(Failure.Undefined)
}