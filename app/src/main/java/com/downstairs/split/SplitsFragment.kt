package com.downstairs.split

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.R
import com.downstairs.eatat.core.extensions.getCoreComponent
import com.downstairs.injection.DaggerAppComponent
import kotlinx.android.synthetic.main.splits_fragment.*
import javax.inject.Inject

class SplitsFragment : Fragment(R.layout.splits_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplitsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSplitsView()
        setupObservers()
    }

    private fun setupSplitsView() {
        splitsRecyclerView.adapter = SplitsAdapter()
        splitsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.splits.observe(viewLifecycleOwner, Observer {
            getSplitsAdapter()?.submitList(it)
        })
    }

    private fun getSplitsAdapter(): SplitsAdapter? = splitsRecyclerView.adapter as? SplitsAdapter
}

