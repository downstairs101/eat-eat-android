package com.downstairs.eatat.core.tools

import android.os.Bundle
import androidx.annotation.IdRes
import com.downstairs.eatat.core.R
import java.io.IOException

sealed class Action

sealed class State : Action() {
    object Success : State()
    object Loading : State()
    data class Failure(val error: Error) : State()
}

sealed class Error {
    object InternetConnection : Error()
    object Unauthorized : Error()
}

class Navigate(@IdRes val destination: Int, val param: Bundle? = null) : Action()

abstract class ViewState {
    fun success(): Action = State.Success
    fun loading(): Action = State.Loading
    abstract fun failure(error: Throwable): Action
}

object MyViewState : ViewState() {

    override fun failure(error: Throwable): Action {
        return when (error) {
            is IOException -> State.Failure(Error.InternetConnection)
            else -> State.Failure(Error.Unauthorized)
        }
    }

    fun navToPlace(): Navigate {
        return Navigate(R.id.textView)
    }
}

fun bla(action: Action) {
    when (action) {
        is Navigate -> print(action.destination)
        is State -> print(action)
    }
}