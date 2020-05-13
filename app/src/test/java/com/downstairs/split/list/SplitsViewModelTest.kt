package com.downstairs.split.list

import androidx.lifecycle.Observer
import com.downstairs.eatat.core.tools.Failure
import com.downstairs.eatat.core.tools.State
import com.downstairs.split.Split
import com.downstairs.split.data.SplitUiModel
import com.downstairs.split.data.User
import com.downstairs.tools.InstantTaskRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
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
            argThat { first().payerName == "Some Payer" }
        )
    }

    @Test
    fun `emits success instruction to view on success split fetch`() {
        val interactor = successResultInteractor()

        val viewModel = getViewModel(interactor)

        assertThat(viewModel.viewState.value).isEqualTo(State.Success)
    }

    @Test
    fun `emits failure instruction to view on failed split fetch`() {
        val interactor = failureResultInteractor()

        val viewModel = getViewModel(interactor)

        assertThat(viewModel.viewState.value).isEqualTo(State.Failed(Failure.Undefined))
    }

    private fun successResultInteractor(vararg split: Split = arrayOf(getSplit())) =
        mock<SplitsInteractor> {
            onBlocking { fetchSpits() } doReturn Result.success(split.toList())
        }

    private fun failureResultInteractor() =
        mock<SplitsInteractor> {
            onBlocking { fetchSpits() } doReturn Result.failure(Throwable("Error on load splits"))
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

    private fun <T> mockObserverFunction() = mock<(T) -> Unit>()
}