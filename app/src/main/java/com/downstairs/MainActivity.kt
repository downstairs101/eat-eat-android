package com.downstairs

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(placeListToolbar)
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.mainFragmentContainer)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.place_list_visualization_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}


