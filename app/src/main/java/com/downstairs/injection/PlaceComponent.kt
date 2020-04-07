package com.downstairs.injection

import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.place.data.PlaceDAO
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class], dependencies = [CoreComponent::class])
interface PlaceComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): PlaceComponent
    }

    fun getPlaceDAO(): PlaceDAO
}