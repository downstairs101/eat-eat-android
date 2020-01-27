package com.downstairs.place.details

import androidx.lifecycle.Observer
import com.downstairs.InstantTaskRule
import com.downstairs.place.data.PlaceEntity
import com.downstairs.place.data.PlaceRepository
import com.nhaarman.mockitokotlin2.check
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlaceDetailsViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @Mock
    lateinit var placeRepository: PlaceRepository

    private lateinit var viewModel: PlaceDetailsViewModel

    @Before
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = PlaceDetailsViewModel(placeRepository)
    }

    @Test
    internal fun `fetch place when place id is not null`() {
        viewModel.fetchPlace("123")

        verifyBlocking(placeRepository) { getPlace("123") }
    }

    @Test
    internal fun `changes view to read only state on fetch place`() {
        val viewStateFunction = mock<Observer<PlaceDetailsViewModel.ViewState>>()

        viewModel.fetchPlace("123")

        viewModel.viewState.observeForever(viewStateFunction)

        verify(viewStateFunction).onChanged(PlaceDetailsViewModel.ViewState.READ_ONLY_STATE)

    }

    @Test
    internal fun `set view to write mode when place id is minor than zero`() {
        val viewStateFunction = mock<Observer<PlaceDetailsViewModel.ViewState>>()

        viewModel.fetchPlace("")

        viewModel.viewState.observeForever(viewStateFunction)

        verify(viewStateFunction).onChanged(PlaceDetailsViewModel.ViewState.WRITE_STATE)
    }

    @Test
    internal fun `tells place repository to insert the given place`() {
        val place = placeDetailsData()

        viewModel.savePlace(place)

        verify(placeRepository).insert(
            check {
                assertThat(it.name).isEqualTo("PlaceEntity Test")
                assertThat(it.category).isEqualTo("Category Test")
                assertThat(it.description).isEqualTo("Some Description")
            }
        )
    }

    private fun placeDetailsData() =
        PlaceDetailsData("123", "PlaceEntity Test", "Category Test", "Some Description")

    private fun getPlace() =
        PlaceEntity("123", "PlaceEntity Test", "Category Test", "Some Description")
}