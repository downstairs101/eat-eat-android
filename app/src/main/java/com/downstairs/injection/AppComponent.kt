package com.downstairs.injection

import android.content.Context
import com.downstairs.EatEatApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<EatEatApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}