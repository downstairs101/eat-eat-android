package com.downstairs.place.details


import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.downstairs.TestApplication
import com.downstairs.injection.ViewModelFactory
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class PlaceDetailsActivityTest {

    val testApplication = getApplicationContext<TestApplication>()

//    @Inject
//    private lateinit var viewModelFactory: ViewModelFactory

    private val scenario = activityScenarioRule<PlaceDetailsActivity>().scenario

    @Before
    fun setUp() {

        scenario.onActivity {
            print(it)
        }

    }
}