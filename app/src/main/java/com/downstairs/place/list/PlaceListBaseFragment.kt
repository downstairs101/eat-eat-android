package com.downstairs.place.list

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.downstairs.R
import javax.inject.Inject

abstract class PlaceListBaseFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    internal val viewModel by lazy {
        ViewModelProvider(this, factory).get(PlaceListViewModel::class.java)
    }

    internal fun prepareListAdapter(@LayoutRes layoutId: Int) =
        PlaceAdapter(layoutId).apply {

            viewModel.places.observe(this@PlaceListBaseFragment,
                Observer { placeList -> submitList(placeList) }
            )

            setOnClickListener { editPlace(it.id) }
        }

    private fun editPlace(placeId: String) {
        val args = Bundle().apply { putString("placeId", placeId) }
        findNavController().navigate(R.id.navigateToPlaceDetailsActivity, args)
    }
}