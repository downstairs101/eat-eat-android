package com.downstairs.tosplit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.downstairs.core.extensions.getCoreComponent
import com.downstairs.core.extensions.navigateUp
import com.downstairs.core.http.HttpManager
import com.downstairs.tosplit.injection.DaggerAppComponent
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

    override fun onBackPressed() {
        findNavController(R.id.mainFragmentContainer).navigateUp(this)
    }
}




