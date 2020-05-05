package com.downstairs.eatat.core.tools

import android.os.Bundle
import androidx.annotation.IdRes

sealed class Action

sealed class State : Action() {
    object Success : State()
    object Loading : State()
    data class Failure(val error: Error) : State()
}

class Navigation(@IdRes val destination: Int, val arguments: Bundle? = null) : Action()

sealed class Error {
    object Undefined : Error()
    object InternetConnection : Error()
    object Unauthorized : Error()
}

abstract class ViewAction {
    open fun success(): Action = State.Success
    open fun loading(): Action = State.Loading
    open fun failure(): State {
        return State.Failure(Error.Undefined)
    }
}