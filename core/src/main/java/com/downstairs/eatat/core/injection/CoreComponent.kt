package com.downstairs.eatat.core.injection

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CoreModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
}