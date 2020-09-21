package com.downstairs.tosplit.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.downstairs.tosplit.R
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.splash_fragment) {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        splashViewModel.checkUserStatus()
    }
}