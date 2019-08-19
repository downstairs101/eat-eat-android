package com.downstairs.place.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.downstairs.R
import com.downstairs.databinding.PlaceDetailsActivityBinding
import com.downstairs.functions.bindLayout
import com.downstairs.functions.openSoftKeyBoard
import com.downstairs.functions.setTransitionListener
import com.google.android.material.textfield.TextInputLayout
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

        setupActionBar()
        animateViewEntry()
        setDataObservers()
        setViewListeners()
    }

    private fun bind() {
        val binding = bindLayout<PlaceDetailsActivityBinding>(this, R.layout.place_details_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()

        val placeId = intent.getLongExtra("placeId", -1)
        fetchPlace(placeId)
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
        viewModel.changeViewState(PlaceDetailsViewModel.ViewState(isReadOnly))
        item.isChecked = !isReadOnly
    }

    private fun setViewListeners() {
        nameInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) view.openSoftKeyBoard()
        }
    }

    private fun setDataObservers() {
        viewModel.viewState.observe(this, Observer { viewStateChanged(it) })
    }

    private fun viewStateChanged(viewState: PlaceDetailsViewModel.ViewState) {
        if (viewState.isInWriteMode) {
            viewToWriteMode()
        } else {
            viewToReadMode()
        }
    }

    private fun fetchPlace(placeId: Long) {
        viewModel.fetchPlace(placeId)
    }

    private fun viewToWriteMode() {
        isViewsEnabled(true)
        setMenuItemDrawable(R.drawable.ic_save)
        requestFocusToFirstEditText()
    }

    private fun viewToReadMode() {
        isViewsEnabled(false)
        setMenuItemDrawable(R.drawable.ic_edit_pencil)
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

    private fun setMenuItemDrawable(drawableId: Int) {
        placeDetailsToolbar.post {
            val menuItem = placeDetailsToolbar.menu.findItem(R.id.editPlaceDetailsMenu)
            menuItem?.icon = getDrawable(drawableId)
        }
    }
}

