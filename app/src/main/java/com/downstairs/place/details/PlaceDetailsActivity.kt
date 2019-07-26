package com.downstairs.place.details


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.downstairs.databinding.PlaceDetailsActivityBinding
import com.downstairs.functions.openSoftKeyBoard
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.place_details_activity.*
import javax.inject.Inject
import android.view.View
import android.view.animation.Animation
import com.downstairs.R
import com.downstairs.functions.displayHeight


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

        animateViewsEntry()
        setDataObservers()
        setupActionBar()
        setViewListeners()
    }

    private fun bind() {
        val binding = bindLayout(R.layout.place_details_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun animateViewsEntry() {
        formContainer.alpha = 0f

        formContainer.post {
            val animatorList = mutableListOf<ObjectAnimator>()
            animatorList.add(ObjectAnimator.ofFloat(formContainer, "alpha", 0f, 1f).apply {
                duration = 200
            })

            var delay = 100L
            formContainer.children.forEach {
                val from = displayHeight() + it.height.toFloat()
                val target = 0f

                animatorList.add(ObjectAnimator.ofFloat(it, "y", from, it.top - target).apply {

                    duration = 350
                    startDelay = delay
                    delay += 50
                })
            }

            animatorList.forEach { it.start() }
        }

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
        val isReadOnly = item.isChecked
        viewModel.changeViewState(PlaceDetailsViewModel.ViewState(isReadOnly))
        item.isChecked = !isReadOnly
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
        viewModel.viewState.observe(this, Observer { viewStateChanged(it) })
    }

    private fun viewStateChanged(viewState: PlaceDetailsViewModel.ViewState) {
        if (viewState.isInWriteMode) {
            viewToWriteMode()
        } else {
            viewToReadMode()
        }
    }

    private fun fetchPlace(placeId: Int?) {
        viewModel.fetchPlace(placeId)
    }

    private fun viewToWriteMode() {
        isViewsEnabled(true)
        setMenuItemDrawable(R.drawable.ic_list)
        nameTextInput.requestFocus()
    }

    private fun viewToReadMode() {
        isViewsEnabled(false)
        setMenuItemDrawable(R.drawable.ic_edit_pencil)
    }

    private fun isViewsEnabled(isEnabled: Boolean) {
        formContainer.children.forEach { it.isEnabled = isEnabled }
    }

    private fun setMenuItemDrawable(drawableId: Int) {
        val menuItem = placeDetailsToolbar.menu.findItem(R.id.editPlaceDetailsMenu)
        menuItem?.icon = getDrawable(drawableId)
    }
}
