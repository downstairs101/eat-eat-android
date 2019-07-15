package com.downstairs

import android.app.Activity
import android.app.Application
import com.downstairs.injection.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class EatEatApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(applicationContext).inject(this)
    }

    override fun activityInjector() = activityInjector
}
