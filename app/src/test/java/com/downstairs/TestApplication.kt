package com.downstairs

class TestApplication : EatEatApplication() {

    override fun onCreate() {
        super.onCreate()
        DaggerTestApp
    }
}