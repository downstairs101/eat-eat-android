package com.downstairs.place.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.downstairs.R
import com.downstairs.databinding.PlaceDetailsActivityBinding
import kotlinx.android.synthetic.main.place_details_activity.*

class PlaceDetailsActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(PlaceDetailsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<PlaceDetailsActivityBinding>(this, R.layout.place_details_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setSupportActionBar(placeDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.onBackPressed()
            R.id.editPlaceDetailsMenu -> viewModel.enterOnEditMode()
        }
        return super.onOptionsItemSelected(item)
    }
}