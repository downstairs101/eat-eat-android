package com.downstairs

import android.app.Application
import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.CoreInjector
import com.downstairs.core.injection.DaggerCoreComponent

open class ToSplitApplication : Application(), CoreInjector {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }
}
