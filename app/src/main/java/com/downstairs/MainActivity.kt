package com.downstairs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.downstairs.eatat.core.extensions.getCoreComponent
import com.downstairs.eatat.core.http.HttpManager
import com.downstairs.eatat.core.injection.ViewModelFactory
import com.downstairs.injection.DaggerAppComponent
import com.downstairs.split.SplitsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    @Inject
    lateinit var httpManager: HttpManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(SplitsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.factory()
            .create(getCoreComponent())
            .inject(this)

        super.onCreate(savedInstanceState)

        print(httpManager)
    }
}




