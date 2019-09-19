package com.downstairs.place.list

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class PlaceListBaseFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    internal val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(PlaceListVIewModel::class.java)
    }

    internal fun prepareListAdapter(@LayoutRes layoutId: Int) =
        PlaceAdapter(layoutId).apply {

            viewModel.places().observe(this@PlaceListBaseFragment,

                Observer { placeList -> submitList(placeList) }
            )

            setOnClickListener { editPlace(it.id) }
        }

    internal abstract fun editPlace(placeId: Long)
}