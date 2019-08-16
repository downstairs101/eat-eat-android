package com.downstairs.place.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import com.downstairs.place.details.PlaceDetailsActivity
import com.downstairs.place.details.PlaceDetailsViewModel
import com.downstairs.place.model.Place
import kotlinx.android.synthetic.main.place_list_fragment.*
import javax.inject.Inject

class PlaceListFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(PlaceListVIewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val placeList = mutableListOf<PlaceListItem>()
        val placeAdapter = PlaceAdapter(R.layout.place_list_item, placeList.toList())

        placeAdapter.setOnClickListener {
            startActivity(Intent(context, PlaceDetailsActivity::class.java))
        }

        placeListRecyclerView.layoutManager = LinearLayoutManager(context)
        placeListRecyclerView.adapter = placeAdapter
    }
}
