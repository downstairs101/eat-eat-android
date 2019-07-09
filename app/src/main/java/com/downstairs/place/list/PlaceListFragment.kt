package com.downstairs.place.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import com.downstairs.place.PlaceListItem
import com.downstairs.place.details.PlaceDetailsActivity
import kotlinx.android.synthetic.main.place_list_fragment.*

class PlaceListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val placeAdapter = PlaceAdapter(
            R.layout.place_list_item, listOf(
                PlaceListItem("Example", "Some Category", "A very good place"),
                PlaceListItem(
                    "Second Example",
                    "Some Category",
                    "Another very good place"
                )
            )
        )

        placeAdapter.setOnClickListener {
            startActivity(Intent(context, PlaceDetailsActivity::class.java))
        }

        placeListRecyclerView.layoutManager = LinearLayoutManager(context)
        placeListRecyclerView.adapter = placeAdapter
    }
}
