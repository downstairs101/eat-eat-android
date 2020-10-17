package com.downstairs.tosplit.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.downstairs.core.extensions.getCoreComponent
import com.downstairs.core.extensions.navigate
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.tosplit.R
import com.downstairs.tosplit.injection.DaggerAppComponent
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.splash_fragment) {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.factory()
            .create(context.getCoreComponent())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()

        viewModel.checkUserStatus()
    }

    private fun setupObservers() {
        viewModel.viewInstruction.observe(viewLifecycleOwner, Observer { onInstructionChange(it) })
    }

    private fun onInstructionChange(instruction: Instruction) {
        when (instruction) {
            is Direction -> findNavController().navigate(instruction)
        }
    }
}