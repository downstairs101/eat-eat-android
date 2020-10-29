package com.downstairs.tosplit.user

import javax.inject.Inject

class UserComplianceInteractor @Inject constructor(private val userRepository: UserRepository) {

    suspend fun checkUserCompliance(): UserComplianceResult {
        return try {
            userRepository.getUser()

            UserComplianceResult.Noncompliance
        } catch (error: Throwable) {
            UserComplianceResult.Noncompliance
        }
    }
}