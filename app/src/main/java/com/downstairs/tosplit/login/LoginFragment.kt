package com.downstairs.tosplit.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.auth.GoogleAuth
import com.downstairs.core.extensions.getCoreComponent
import com.downstairs.tosplit.R
import com.downstairs.tosplit.injection.DaggerAppComponent
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.login_fragment) {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerAppComponent
            .factory()
            .create(context.getCoreComponent())
            .inject(this)
    }

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