package com.downstairs.place.details


import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.activityScenarioRule
import com.downstairs.TestApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class PlaceDetailsActivityTest {

    val testApplication = getApplicationContext<TestApplication>()

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val scenario = launchActivity<PlaceDetailsActivity>()

    @Before
    fun setUp() {

        scenario.onActivity {
            print(it)
        }
    }

    @Test
    fun name() {
        print("")
    }
}