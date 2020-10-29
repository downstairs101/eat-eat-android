package com.downstairs.tosplit.user

sealed class UserComplianceResult {
    object Noncompliance : UserComplianceResult()
}
