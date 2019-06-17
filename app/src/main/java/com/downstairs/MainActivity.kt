package com.downstairs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navController = findNavController(R.id.mainFragmentContainer)

        placeListToolbar.title = getString(R.string.app_name)
        placeListToolbar.setOnMenuItemClickListener {
            if (it.isChecked) {
                navController.navigate(R.id.actionPlacePageToPlaceListFragment)
                it.onNavDestinationSelected(navController)
            } else {
                navController.navigate(R.id.actionPlaceListToPlacePageFragment)
                it.onNavDestinationSelected(navController)
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val menuItem = placeListToolbar.menu.findItem(R.id.placesVisualizationMenu)

            if (destination.id == R.id.placeListFragment) {
                menuItem.icon = getDrawable(R.drawable.ic_list)
                menuItem.isChecked = false
            } else {
                menuItem.isChecked = true
                menuItem.icon = getDrawable(R.drawable.ic_carousel)
            }
        }
    }
}


