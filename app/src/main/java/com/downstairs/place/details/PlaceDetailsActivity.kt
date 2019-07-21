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

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(PlaceDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        bind()

        setDataObservers()
        setupActionBar()
        setViewListeners()
    }

    override fun onStart() {
        super.onStart()
        fetchPlace(null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.onBackPressed()
            R.id.editPlaceDetailsMenu -> setupViewState(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewState(item: MenuItem) {
        val toEdit = item.isChecked
        viewModel.setupViewState(toEdit)
    }


    private fun bind() {
        val binding = bindLayout(R.layout.place_details_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun bindLayout(layoutId: Int) =
        DataBindingUtil.setContentView<PlaceDetailsActivityBinding>(this, layoutId)

    private fun setupActionBar() {
        setSupportActionBar(placeDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
    }

    private fun setViewListeners() {
        nameTextInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) view.openSoftKeyBoard()
        }
    }

    private fun setDataObservers() {
        viewModel.editableState.observe(this, Observer { isOnEditMode -> editMode(isOnEditMode) })
    }

    private fun fetchPlace(placeId: Int?) {
        viewModel.fetchPlace(placeId)
    }

    private fun editMode(isOnEditMode: Boolean) {
        formContainer.children.forEach { it.isEnabled = isOnEditMode }
        nameTextInput.requestFocus()
    }
}
