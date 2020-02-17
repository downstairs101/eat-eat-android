package com.downstairs

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(placeListToolbar)
        setListeners()

        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
    }

    override fun onStart() {
        super.onStart()
        val navController = getNavController()

        placeListToolbar.setOnMenuItemClickListener {
            if (it.isChecked) {
                navController.navigate(R.id.actionPlacePageToPlaceListFragment)
                it.icon = getDrawable(R.drawable.ic_list)
                it.isChecked = false
                it.onNavDestinationSelected(navController)
            } else {
                navController.navigate(R.id.actionPlaceListToPlacePageFragment)
                it.isChecked = true
                it.icon = getDrawable(R.drawable.ic_carousel)
                it.onNavDestinationSelected(navController)
            }
        }
    }

    private fun getNavController() = findNavController(R.id.mainFragmentContainer)

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.place_list_visualization_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setListeners() {
        addPlaceButton.setOnClickListener {
            getNavController().navigate(R.id.navigateToPlaceDetailsActivity)
        }
    }
}


