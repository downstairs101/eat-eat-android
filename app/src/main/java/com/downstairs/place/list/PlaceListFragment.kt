package com.downstairs.place.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import com.downstairs.place.details.PlaceDetailsActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.place_list_fragment.*
import javax.inject.Inject

class PlaceListFragment : PlaceListBaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(PlaceListVIewModel::class.java)
    }

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
        val placeAdapter = prepareListAdapter(viewModel, R.layout.place_list_item)

        placeListRecyclerView.layoutManager = LinearLayoutManager(context)
        placeListRecyclerView.adapter = placeAdapter
    }

    override fun editPlace(placeId: Long) {
        val intent = Intent(context, PlaceDetailsActivity::class.java)
        intent.putExtra("placeId", placeId)

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updatePlaceList()
    }
}
