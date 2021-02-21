package com.downstairs.core.tools

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val error: Throwable) : Result<T>()


    val isSuccess: Boolean
        get() = this is Success<*>

    val isFailure: Boolean
        get() = this is Failure<*>


    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (isSuccess) action((this as Success<T>).value)
        return this
    }

    inline fun onError(action: (Throwable) -> Unit): Result<T> {
        if (isFailure) action((this as Failure<*>).error)
        return this
    }
}

sealed class Completable {
    object Complete : Completable()
    data class Failure<T : Throwable>(val error: T) : Completable()

    val isCompleted: Boolean
        get() = this is Complete

    val isFailure: Boolean
        get() = this is Failure<*>

    inline fun onComplete(action: () -> Unit): Completable {
        if (isCompleted) action()
        return this
    }

    inline fun onError(action: (Throwable) -> Unit): Completable {
        if (isFailure) action((this as Failure<*>).error)
        return this
    }
}
