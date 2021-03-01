package com.downstairs.tosplit.login

import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.auth.AuthManager
import com.downstairs.core.auth.method.AuthMethod
import com.downstairs.tosplit.user.UserNotFoundException
import com.downstairs.tosplit.user.data.UserRepository
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val authManager: AuthManager,
    private val userRepository: UserRepository
) {

    suspend fun authorize(authMethod: AuthMethod): AuthResult {
        return try {
            authManager.authorize(authMethod)

            checkUserCompliance()
        } catch (error: Throwable) {
            AuthResult.Unauthorized
        }
    }

    suspend fun check(): AuthResult {
        return if (authManager.isUserAuthorized()) {
            checkUserCompliance()
        } else {
            AuthResult.Unauthorized
        }
    }

    private suspend fun checkUserCompliance(): AuthResult {
        return try {
            userRepository.getUser()

            AuthResult.Compliance
        } catch (error: Throwable) {
            parseComplianceError(error)
        }
    }

    private fun parseComplianceError(error: Throwable): AuthResult {
        return if (error is UserNotFoundException) {
            AuthResult.Noncompliance
        } else {
            AuthResult.Undefined
        }
    }


    fun processAuthResult(authResultData: AuthResultData) {
        authManager.onResult(authResultData)
    }
}

sealed class AuthResult {
    object Compliance : AuthResult()
    object Noncompliance : AuthResult()
    object Undefined : AuthResult()
    object Unauthorized : AuthResult()
}

//@RunWith(MockitoJUnitRunner::class)
//class UserComplianceInteractorTest {
//
//    private lateinit var interactor: UserComplianceInteractor
//
//    @Mock
//    lateinit var userRepository: UserRepository
//
//    @Before
//    fun setUp() {
//        interactor = UserComplianceInteractor(userRepository)
//    }
//
//    @Test
//    fun `returns user compliance result when user does not exists`() = runBlockingTest {
//        whenever(userRepository.getUser()).thenReturn(User("Test User"))
//
//        val result = interactor.checkUserCompliance()
//
//        assertThat(result).isEqualTo(UserComplianceResult.Compliance)
//    }
//
//    @Test
//    fun `returns user noncompliance result when user does not exists`() = runBlockingTest {
//        whenever(userRepository.getUser()).then { throw UserNotFoundException() }
//
//        val result = interactor.checkUserCompliance()
//
//        assertThat(result).isEqualTo(UserComplianceResult.Noncompliance)
//    }
//
//    @Test
//    fun `returns undefined result on user compliance check error`() = runBlockingTest {
//        whenever(userRepository.getUser()).then { throw Throwable() }
//
//        val result = interactor.checkUserCompliance()
//
//        assertThat(result).isEqualTo(UserComplianceResult.Undefined)
//    }
//}