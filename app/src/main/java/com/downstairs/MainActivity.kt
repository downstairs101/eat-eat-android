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

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.factory()
            .create(getCoreComponent())
            .inject(this)

        super.onCreate(savedInstanceState)

        print(httpManager)
    }
}




