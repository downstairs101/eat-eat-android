package com.downstairs.split.list

import androidx.lifecycle.Observer
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.State
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
        val interactor = successResultInteractor()
        val viewModel = getViewModel(interactor)

        viewModel.splits.observeForever(observer)

        verify(observer).onChanged(
            argThat { first().id == 1 }
        )
    }

    @Test
    fun `emits success instruction to view on success split fetch`() = runBlocking {
        val observer = mock<Observer<Instruction>>()
        val interactor = successResultInteractor()
        val viewModel = getViewModel(interactor)

        viewModel.viewState.observeForever(observer)

        verify(observer).onChanged(
            argThat { this is State.Success }
        )
    }

    private suspend fun successResultInteractor(vararg split: Split = arrayOf(getSplit())): SplitsInteractor {
        return mock {
            val result = Result.success(split.toList())
            whenever(it.fetchSpits()).thenReturn(result)
        }
    }

    private fun getViewModel(splitsInteractor: SplitsInteractor): SplitsViewModel {
        return SplitsViewModel(SplitsViewInstruction(), splitsInteractor)
    }

    private fun getSplit(
        id: Int = 1,
        name: String = "Car rent",
        user: User = User("Some Payer"),
        value: Double = 230.00
    ) = Split(id, name, user, value)

}