package com.downstairs.place.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import com.downstairs.injection.getApplicationComponent
import com.downstairs.place.injection.DaggerPlaceComponent
import com.downstairs.place.injection.PlaceModule
import kotlinx.android.synthetic.main.place_list_fragment.*

class PlaceListFragment : PlaceListBaseFragment() {

    override fun onAttach(context: Context) {
        DaggerPlaceComponent.factory()
            .create(context.getApplicationComponent(), PlaceModule())
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.place_list_fragment, container, false)


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
