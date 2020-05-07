package com.downstairs

import android.app.Application
import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.CoreInjector
import com.downstairs.eatat.core.injection.DaggerCoreComponent

open class ToSplitApplication : Application(), CoreInjector {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }
}
