package com.downstairs.tosplit.split.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.downstairs.core.extensions.getCoreComponent
import com.downstairs.core.extensions.navigate
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.State
import com.downstairs.tosplit.R
import com.downstairs.tosplit.split.injection.DaggerSplitComponent
import kotlinx.android.synthetic.main.split_list_fragment.*
import javax.inject.Inject

class SplitListFragment : Fragment(R.layout.split_list_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplitsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerSplitComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadSplits()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSplitsList()
        setupObservers()
        setupListeners()
    }

    private fun setupSplitsList() {
        splitsRecyclerView.adapter = SplitAdapter()
        splitsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer() {
            onReceiveInstruction(it)
        })

        viewModel.splits.observe(viewLifecycleOwner, Observer {
            getSplitsAdapter()?.submitList(it)
        })
    }

    private fun setupListeners() {
        getSplitsAdapter()?.setOItemClickListener {
            viewModel.onItemClick(it)
        }
    }

    private fun onReceiveInstruction(instruction: Instruction) {
        when (instruction) {
            is State.Success -> toSuccessState()
            is State.Loading -> toLoadingState()
            is State.Failed -> toFailedState()
            is Direction -> findNavController().navigate(instruction)
        }
    }

    private fun toSuccessState() {
        freeViews()
    }

    private fun toFailedState() {
        freeViews()
        Toast.makeText(context, "Error on load splits", Toast.LENGTH_LONG).show()
    }

    private fun toLoadingState() {
        splitsProgressBar.isGone = false
    }

    private fun freeViews() {
        splitsProgressBar.isGone = true
    }

    private fun getSplitsAdapter(): SplitAdapter? = splitsRecyclerView.adapter as? SplitAdapter
}

