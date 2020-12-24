package com.downstairs.tosplit.user

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserComplianceInteractorTest {

    private lateinit var interactor: UserComplianceInteractor

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        interactor = UserComplianceInteractor(userRepository)
    }

    @Test
    fun `returns user compliance result when user does not exists`() = runBlockingTest {
        whenever(userRepository.getUser()).thenReturn(User("Test User"))

        val result = interactor.checkUserCompliance()

        assertThat(result).isEqualTo(UserComplianceResult.Compliance)
    }

    @Test
    fun `returns user noncompliance result when user does not exists`() = runBlockingTest {
        whenever(userRepository.getUser()).then { throw UserNotFoundException() }

        val result = interactor.checkUserCompliance()

        assertThat(result).isEqualTo(UserComplianceResult.Noncompliance)
    }

    @Test
    fun `returns undefined result on user compliance check error`() = runBlockingTest {
        whenever(userRepository.getUser()).then { throw Throwable() }

        val result = interactor.checkUserCompliance()

        assertThat(result).isEqualTo(UserComplianceResult.Undefined)
    }
}