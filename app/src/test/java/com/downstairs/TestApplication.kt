package com.downstairs

import com.downstairs.androidxtest.injection.DaggerTestAppComponent

class TestApplication : EatEatApplication() {

    override fun onCreate() {
        super.onCreate()

        DaggerTestAppComponent.factory().create(this).inject(this)
    }
}