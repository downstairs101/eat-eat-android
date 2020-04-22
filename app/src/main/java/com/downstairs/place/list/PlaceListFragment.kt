package com.downstairs.place.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import com.downstairs.eatat.core.extensions.getCoreComponent
import com.downstairs.injection.DaggerPlaceComponent
import kotlinx.android.synthetic.main.place_list_fragment.*

class PlaceListFragment : PlaceListBaseFragment() {

    override fun onAttach(context: Context) {
        DaggerPlaceComponent.factory()
            .create(context.getCoreComponent())
            .inject(this)

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val placeAdapter = prepareListAdapter(R.layout.place_list_item)
        placeListRecyclerView.layoutManager = LinearLayoutManager(context)
        placeListRecyclerView.adapter = placeAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.updatePlaceList()
    }
}
