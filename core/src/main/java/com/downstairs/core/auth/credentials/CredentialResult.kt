package com.downstairs.core.auth.credentials

sealed class CredentialResult {
    class ValidCredential(val idToken: String) : CredentialResult()
    object InvalidCredential : CredentialResult()
}