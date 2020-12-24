package com.downstairs.tosplit

import android.app.Application
import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.CoreInjector
import com.downstairs.core.injection.DaggerCoreComponent
import com.downstairs.core.notification.NotificationChannelManager
import com.google.android.gms.cast.framework.CastContext

open class ToSplitApplication : Application(), CoreInjector {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        CastContext.getSharedInstance(this)

        NotificationChannelManager(baseContext).createPlayerChannel()
    }
}
