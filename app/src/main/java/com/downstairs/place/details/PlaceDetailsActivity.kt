package com.downstairs.place.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.downstairs.R
import kotlinx.android.synthetic.main.place_details_activity.*

class PlaceDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details_activity)

        val viewModel = ViewModelProviders.of(this).get(PlaceDetailsViewModel::class.java)
        initStreams(viewModel)

        setSupportActionBar(placeDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initStreams(viewModel: PlaceDetailsViewModel) {
        viewModel.getViewEditableState().observe(this, Observer { updateViewEditableState(it) })
    }

    private fun updateViewEditableState(editable: Boolean) {
        formContainer.children.forEach {
            it.isEnabled = editable
        }
    }
}