package com.downstairs.place.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.downstairs.R
import com.downstairs.functions.dipToPixels
import kotlinx.android.synthetic.main.place_page_fragment.*

class PlacePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_page_fragment, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        setupViewPagerVisualSettings()
//        setViewPagerItems()
//    }
//
//    private fun setViewPagerItems() {
//        placeViewPager.adapter =  val placeAdapter = prepareListAdapter(viewModel, R.layout.place_list_item)
//            R.layout.place_page_item, mutableListOf(
//                PlaceListItem(
//                    0,
//                    "Example",
//                    "Some Category",
//                    "A very good place"
//                ),
//                PlaceListItem(
//                    0, "Second Example",
//                    "Some Category",
//                    "Another very good place"
//                )
//            )
//        )
//    }
//
//    private fun setupViewPagerVisualSettings() {
//        placeViewPager.orientation = ORIENTATION_HORIZONTAL
//        placeViewPager.offscreenPageLimit = 3
//
//        val marginInPixel = context?.dipToPixels(20f) ?: 0
//        placeViewPager.setPageTransformer(MarginPageTransformer(marginInPixel))
//    }
}