package com.downstairs.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
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
        placeListRecyclerView.layoutManager = LinearLayoutManager(context)

        placeListRecyclerView.adapter = PlaceAdapter(
            R.layout.place_list_item, listOf(
                PlaceListItem("Example", "Some Category", "A very good place"),
                PlaceListItem("Second Example", "Some Category", "Another very good place")
            )
        )
    }
}
