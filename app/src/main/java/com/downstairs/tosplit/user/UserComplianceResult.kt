package com.downstairs.tosplit.user

sealed class UserComplianceResult {
    object IncompleteData : UserComplianceResult()
}
