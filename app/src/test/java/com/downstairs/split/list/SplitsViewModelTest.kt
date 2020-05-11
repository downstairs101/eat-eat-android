package com.downstairs.split.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.downstairs.split.Split
import com.downstairs.split.data.SplitUiModel
import com.downstairs.split.data.User
import com.downstairs.tools.InstantTaskRule
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SplitsViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @Test
    fun `emits split list on success fetch result`() = runBlocking {
        val observer = mock<Observer<List<SplitUiModel>>>()

        val interactor = mock<SplitsInteractor> {
            whenever(it.fetchSpits(1))
                .thenReturn(Result.success(listOf(Split(1, "Car rent", User("Some Payer"), 230.00))))
        }
        val viewModel = getViewModel(interactor)

        viewModel.splits.observeForever(observer)

        verify(observer).onChanged(argThat { true })
    }

    private fun getViewModel(splitsInteractor: SplitsInteractor): SplitsViewModel {
        return SplitsViewModel(SplitsViewInstruction(), splitsInteractor)
    }
}