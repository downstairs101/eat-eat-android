package com.downstairs.place.list

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class PlaceListBaseFragment : Fragment() {

    internal fun prepareListAdapter(viewModel: PlaceListVIewModel, @LayoutRes layoutId: Int) =
        PlaceAdapter(layoutId).apply {
            viewModel.places().observe(this@PlaceListBaseFragment,
                Observer { placeList -> submitList(placeList) }
            )

            setOnClickListener { editPlace(it.id) }
        }

    internal abstract fun editPlace(placeId: Long)
}