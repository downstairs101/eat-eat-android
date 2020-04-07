package com.downstairs.injection

import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.FeatureScope
import com.downstairs.place.details.PlaceDetailsActivity
import com.downstairs.place.list.PlaceListFragment
import com.downstairs.place.list.PlacePageFragment
import dagger.Component

@FeatureScope
@Component(modules = [ViewModelModule::class, DataModule::class], dependencies = [CoreComponent::class])
interface PlaceComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): PlaceComponent
    }

    fun inject(activity: PlaceDetailsActivity)
    fun inject(fragment: PlaceListFragment)
    fun inject(fragment: PlacePageFragment)
}