package com.downstairs.core.auth

sealed class AuthResult {

    object Authorized : AuthResult()
    object Unauthorized : AuthResult()
}

sealed class CredentialResult {
    class ValidCredential(val idToken: String) : CredentialResult()
    object InvalidCredential : CredentialResult()
}