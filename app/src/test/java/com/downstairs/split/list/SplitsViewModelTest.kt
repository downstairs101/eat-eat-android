package com.downstairs.split.list

import androidx.lifecycle.Observer
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.State
import com.downstairs.split.Split
import com.downstairs.split.data.SplitUiModel
import com.downstairs.split.data.User
import com.downstairs.tools.InstantTaskRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@RunWith(JUnit4::class)
class SplitsViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @Mock
    lateinit var interactor: SplitsInteractor

    private lateinit var viewModel: SplitsViewModel

    @Before
    fun setUp() {
        initMocks(this)

        viewModel = SplitsViewModel(SplitsViewInstruction(), interactor)
    }

    @Test
    fun `emits split list on success fetch result`() = runBlocking {
        val observer = mock<Observer<List<SplitUiModel>>>()
        stubLoadSplitsSuccessResult()

        viewModel.splits.observeForever(observer)
        viewModel.loadSplits()

        verify(observer).onChanged(
            argThat { first().payerName == "Some Payer" }
        )
    }

    @Test
    fun `emits loading instruction to view on starts to fetch splits`() {
        val observer = mock<Observer<Instruction>>()
        stubLoadSplitsSuccessResult()

        viewModel.viewState.observeForever(observer)
        viewModel.loadSplits()

        verify(observer).onChanged(isA<State.Loading>())
    }

    @Test
    fun `emits success instruction to view on success split fetch`() {
        val observer = mock<Observer<Instruction>>()
        stubLoadSplitsSuccessResult()

        viewModel.viewState.observeForever(observer)
        viewModel.loadSplits()

        verify(observer).onChanged(isA<State.Success>())
    }

    @Test
    fun `emits failure instruction to view on failed split fetch`() {
        val observer = mock<Observer<Instruction>>()
        stubLoadSplitsFailedResult()

        viewModel.viewState.observeForever(observer)
        viewModel.loadSplits()

        verify(observer).onChanged(isA<State.Failed>())
    }

    private fun stubLoadSplitsSuccessResult(vararg split: Split = arrayOf(getSplit())) = runBlocking {
        whenever(interactor.fetchSpits()) doReturn Result.success(split.toList())
    }

    private fun stubLoadSplitsFailedResult() = runBlocking {
        whenever(interactor.fetchSpits()) doReturn Result.failure(Throwable("Error on load splits"))
    }

    private fun getSplit(
        id: Int = 1,
        name: String = "Car rent",
        user: User = User("Some Payer"),
        value: Double = 230.00
    ) = Split(id, name, user, value)
}