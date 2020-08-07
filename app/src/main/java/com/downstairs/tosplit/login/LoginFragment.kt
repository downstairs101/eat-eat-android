package com.downstairs.tosplit.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.auth.GoogleAuth
import com.downstairs.tosplit.R
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(R.layout.login_fragment) {
    private val viewModel = LoginViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButton.setOnClickListener {
            viewModel.signIn(GoogleAuth(this))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GoogleAuth.GOOGLE_AUTH_REQUEST_CODE -> viewModel.processSignInResult(AuthResultData(data!!))
        }
    }
}