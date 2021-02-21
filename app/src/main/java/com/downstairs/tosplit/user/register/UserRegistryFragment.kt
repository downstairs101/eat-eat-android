package com.downstairs.tosplit.user.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.downstairs.core.extensions.getCoreComponent
import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.Instruction
import com.downstairs.core.tools.instruction.State
import com.downstairs.tosplit.R
import com.downstairs.tosplit.user.data.UserUiModel
import com.downstairs.tosplit.user.injection.DaggerUserComponent
import kotlinx.android.synthetic.main.user_registry_fragment.*
import javax.inject.Inject

class UserRegistryFragment : Fragment(R.layout.user_registry_fragment) {

    @Inject
    lateinit var viewModel: UserRegistryViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerUserComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        userRegisterDoneButton.setOnClickListener {
            viewModel.saveUser(UserUiModel(userNameInputText.text.toString()))
        }
    }

    private fun setupObservers() {
        viewModel.viewInstruction.observe(viewLifecycleOwner, Observer {
            onInstructionChanged(it)
        })
    }

    private fun onInstructionChanged(instruction: Instruction) {
        when(instruction){
            is State.Loading-> print("Carregando")
            is Direction -> findNavController().navigate(instruction.direction)
        }
    }
}