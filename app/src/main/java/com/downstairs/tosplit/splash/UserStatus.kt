package com.downstairs.tosplit.splash

sealed class UserStatus {
    object Authorized : UserStatus()
    object Unauthorized : UserStatus()
    object Incomplete : UserStatus()
}