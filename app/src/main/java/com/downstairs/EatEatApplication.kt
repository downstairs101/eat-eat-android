package com.downstairs

import android.app.Application
import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.CoreInjector
import com.downstairs.eatat.core.injection.DaggerCoreComponent

open class EatEatApplication : Application(), CoreInjector {

    override val coreComponent: CoreComponent
        get() = DaggerCoreComponent.factory().create(applicationContext)
}
