package com.downstairs.place.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.place_list_fragment.*

class PlaceListFragment : PlaceListBaseFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
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
