package com.downstairs.place.details

import com.downstairs.InstantTaskExtension
import com.downstairs.place.model.PlaceRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExtension::class)
class PlaceDetailsViewModelTest {

    @RelaxedMockK
    lateinit var placeRepository: PlaceRepository

    private lateinit var viewModel: PlaceDetailsViewModel

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)

        viewModel = PlaceDetailsViewModel(placeRepository)
    }

    @Test
    internal fun `fetch place when place id is not null`() {
        viewModel.fetchPlace(0)

        coVerify { placeRepository.getPlace(0) }
    }

    @Test
    internal fun `set view to write mode when place id is minor than zero`() {
        val viewStateFunction = mockObserverFunction<PlaceDetailsViewModel.ViewState>()

        viewModel.fetchPlace(-1)

        viewModel.viewState.observeForever(viewStateFunction)

        verify {
            viewStateFunction.invoke(
                withArg {
                    assertThat(it.isInWriteMode).isTrue()
                })
        }
    }

    private fun <T> mockObserverFunction() =
        mockk<(arg: T) -> Unit>(relaxed = true)
}