package com.downstairs.place.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.downstairs.R
import com.downstairs.eatat.core.extensions.getCoreComponent
import com.downstairs.functions.openSoftKeyBoard
import com.downstairs.functions.setTransitionListener
import com.downstairs.functions.stringText
import com.downstairs.injection.DaggerPlaceComponent
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.place_details_activity.*
import javax.inject.Inject

class PlaceDetailsActivity : AppCompatActivity(R.layout.place_details_activity) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(PlaceDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerPlaceComponent
            .factory()
            .create(getCoreComponent())
            .inject(this)

        super.onCreate(savedInstanceState)

        setupActionBar()
        animateViewEntry()
        setDataObservers()
        setViewListeners()
    }

    override fun onStart() {
        super.onStart()
        fetchPlace(intent.getStringExtra("placeId") ?: "")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.onBackPressed()
            R.id.placeDetailsMenu -> setupViewState(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupActionBar() {
        setSupportActionBar(placeDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
    }

    private fun animateViewEntry() {
        placeDetailsContainer.post {
            setTransitionListener()
            startTransition()
        }
    }

    private fun setTransitionListener() {
        placeDetailsContainer.setTransitionListener(onComplete = {
            requestFocusToFirstEditText()
        })
    }

    private fun startTransition() {
        placeDetailsContainer.transitionToEnd()
    }

    private fun setupViewState(item: MenuItem) {
        val isReadOnly = item.isChecked

        if (isReadOnly) {
            viewModel.viewToWriteState()
        } else {
            viewModel.savePlace(
                PlaceDetailsData(
                    name = nameInput.stringText(),
                    category = categoryInput.stringText(),
                    description = descriptionInput.stringText()
                )
            )
        }

        item.isChecked = !isReadOnly
    }

    private fun setViewListeners() {
        nameInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) view.openSoftKeyBoard()
        }
    }

    private fun setDataObservers() {
        viewModel.viewState.observe(this, Observer { viewStateChanged(it) })
        viewModel.placeDetailsData.observe(this, Observer { bindPlace(it) })
    }

    private fun bindPlace(placeDetails: PlaceDetailsData) {
        nameInput.setText(placeDetails.name)
        categoryInput.setText(placeDetails.category)
        descriptionInput.setText(placeDetails.description)

    }

    private fun viewStateChanged(viewState: PlaceDetailsViewModel.ViewState) {
        when (viewState) {
            PlaceDetailsViewModel.ViewState.WRITE_STATE -> viewToWriteMode()

            PlaceDetailsViewModel.ViewState.READ_ONLY_STATE -> viewToReadMode()
        }
    }

    private fun fetchPlace(placeId: String) {
        viewModel.fetchPlace(placeId)
    }

    private fun viewToWriteMode() {
        isViewsEnabled(true)
        setupMenuItem(R.drawable.ic_save, false)
        requestFocusToFirstEditText()
    }

    private fun viewToReadMode() {
        isViewsEnabled(false)
        setupMenuItem(R.drawable.ic_edit_pencil, true)
    }

    private fun requestFocusToFirstEditText() {
        nameInput.requestFocus()
    }

    private fun isViewsEnabled(isEnabled: Boolean) {
        placeDetailsContainer.children.forEach { view ->
            if (view is TextInputLayout) {
                view.isEnabled = isEnabled
            }
        }
    }

    private fun setupMenuItem(drawableId: Int, isChecked: Boolean) {
        placeDetailsToolbar.post {
            val menuItem = placeDetailsToolbar.menu.findItem(R.id.placeDetailsMenu)
            menuItem?.icon = getDrawable(drawableId)
            menuItem?.isChecked = isChecked
        }
    }
}

