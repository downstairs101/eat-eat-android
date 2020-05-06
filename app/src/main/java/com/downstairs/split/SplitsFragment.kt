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
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.Navigation
import com.downstairs.eatat.core.tools.State
import com.downstairs.split.injection.DaggerSplitComponent
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
        DaggerSplitComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSplitsView()
        setupObservers()
    }

    private fun setupSplitsView() {
        splitsRecyclerView.adapter = SplitAdapter()
        splitsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer() {
            onStateChange(it)
        })

        viewModel.splits.observe(viewLifecycleOwner, Observer {
            getSplitsAdapter()?.submitList(it)
        })
    }

    private fun onStateChange(instruction: Instruction) {
        when (instruction) {
            is State.Success -> print("success")
            is State.Loading -> print("loading")
            is State.Failed -> print("error")
            is Navigation -> print("navigating") //findNavController().navigate(action.destination, action.param)
        }
    }

    private fun getSplitsAdapter(): SplitAdapter? = splitsRecyclerView.adapter as? SplitAdapter
}

