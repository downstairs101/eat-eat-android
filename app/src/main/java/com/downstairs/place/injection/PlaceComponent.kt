package com.downstairs.place.injection

import com.downstairs.injection.PlaceComponent
import com.downstairs.place.details.PlaceDetailsActivity
import com.downstairs.place.list.PlaceListFragment
import com.downstairs.place.list.PlacePageFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [PlaceModule::class],
    dependencies = [PlaceComponent::class]
)
interface PlaceComponent {

    @Component.Factory
    interface Factory {
        fun create(
            placeComponent: PlaceComponent,
            placeModule: PlaceModule
        ): PlaceComponent
    }

    fun inject(activity: PlaceDetailsActivity)
    fun inject(fragment: PlaceListFragment)
    fun inject(fragment: PlacePageFragment)
}