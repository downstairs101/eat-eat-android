package com.downstairs.injection

import android.content.Context
import com.downstairs.place.data.PlaceDAO
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun getPlaceDAO(): PlaceDAO
}