package com.downstairs.tosplit.user.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.downstairs.tosplit.R
import kotlinx.android.synthetic.main.user_register_fragment.*

class UserRegisterFragment : Fragment(R.layout.user_register_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
    }

    private fun setupListeners() {
        userRegisterDoneButton.setOnClickListener {
            //viewModel.saveUser()
        }
    }
}