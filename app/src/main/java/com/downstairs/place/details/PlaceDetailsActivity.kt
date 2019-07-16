package com.downstairs.place.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.downstairs.R
import com.downstairs.databinding.PlaceDetailsActivityBinding
import com.downstairs.functions.openSoftKeyBoard
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.place_details_activity.*
import javax.inject.Inject


class PlaceDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(PlaceDetailsViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = bind(R.layout.place_details_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setDataObservers()
        setupActionBar()
        setupViewListeners()
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

    private fun bind(layoutId: Int) =
        DataBindingUtil.setContentView<PlaceDetailsActivityBinding>(this, layoutId)

    private fun setupActionBar() {
        setSupportActionBar(placeDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
    }

    private fun setupViewListeners() {
        nameTextInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.openSoftKeyBoard()
            }
        }
    }

    private fun setDataObservers() {
        viewModel.editableState.observe(this, Observer { isOnEditMode -> editMode(isOnEditMode) })
    }

    private fun editMode(isOnEditMode: Boolean) {
        formContainer.children.forEach { it.isEnabled = isOnEditMode }
        nameTextInput.requestFocus()
    }
}
