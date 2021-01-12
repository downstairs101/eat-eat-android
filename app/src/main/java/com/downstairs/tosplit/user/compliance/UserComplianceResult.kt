package com.downstairs.tosplit.user.compliance

sealed class UserComplianceResult {
    object Compliance : UserComplianceResult()
    object Noncompliance : UserComplianceResult()
    object Undefined : UserComplianceResult()
}
