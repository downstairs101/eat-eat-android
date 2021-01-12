package com.downstairs.tosplit.user.compliance

import com.downstairs.tosplit.user.UserNotFoundException
import com.downstairs.tosplit.user.data.UserRepository
import javax.inject.Inject

class UserComplianceInteractor @Inject constructor(private val userRepository: UserRepository) {

    suspend fun checkUserCompliance(): UserComplianceResult {
        return try {
            userRepository.getUser()

            UserComplianceResult.Compliance
        } catch (error: Throwable) {
            parseComplianceError(error)
        }
    }

    private fun parseComplianceError(error: Throwable): UserComplianceResult {
        return if (error is UserNotFoundException) {
            UserComplianceResult.Noncompliance
        } else {
            UserComplianceResult.Undefined
        }
    }
}