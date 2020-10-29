package com.downstairs.tosplit.user

sealed class UserComplianceResult {
    object Compliance : UserComplianceResult()
    object Noncompliance : UserComplianceResult()
}
