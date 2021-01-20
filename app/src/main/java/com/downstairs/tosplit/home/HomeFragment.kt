package com.downstairs.tosplit.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.downstairs.core.extensions.getCoreComponent
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.tosplit.R
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.home_fragment) {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerHomeComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewInstruction.observe(viewLifecycleOwner, Observer {
            onInstructionChanged(it)
        })
    }

    private fun onInstructionChanged(instruction: Instruction) {
        when (instruction) {
            is Direction -> findNavController().navigate(instruction.direction)
        }
    }
}