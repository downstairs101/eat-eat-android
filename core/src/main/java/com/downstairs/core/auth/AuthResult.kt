package com.downstairs.core.auth

sealed class AuthResult {
    object Authorized : AuthResult()
    object Unauthorized : AuthResult()
}