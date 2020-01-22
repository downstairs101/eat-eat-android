package com.downstairs

import android.app.Application
import com.downstairs.injection.ApplicationComponent
import com.downstairs.injection.CoreInjector
import com.downstairs.injection.DaggerApplicationComponent

open class EatEatApplication : Application(), CoreInjector {

    override val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}
