package com.downstairs.place.details

import com.downstairs.InstantTaskExtension
import com.downstairs.observeOnce
import com.downstairs.place.model.PlaceRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertTrue
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
        viewModel.fetchPlace(-1)

        viewModel.viewState.observeOnce {
            assertTrue(it.isInWriteMode)
        }

    }
}