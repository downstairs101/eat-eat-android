package com.downstairs.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.downstairs.R

class PagePlaceListAdapter(private val places: List<PlaceListItem>) :
    RecyclerView.Adapter<PlaceListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.place_page_list_item, parent, false)

        return PlaceListViewHolder(view)
    }

    override fun getItemCount() = places.size

    override fun onBindViewHolder(viewHolder: PlaceListViewHolder, position: Int) {
        viewHolder.bind(places[position])
    }

}

class PlaceListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(placeListItem: PlaceListItem) {
        val placeName = itemView.findViewById<TextView>(R.id.placeName)
        val placeCategory = itemView.findViewById<TextView>(R.id.placeCategory)
        val placeDescription = itemView.findViewById<TextView>(R.id.placeDescription)

        placeName.text = placeListItem.name
        placeCategory.text = placeListItem.category
        placeDescription.text = placeListItem.description
    }
}
