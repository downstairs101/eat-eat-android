package com.downstairs.place.injection

import com.downstairs.injection.ApplicationComponent
import com.downstairs.injection.FeatureScope
import com.downstairs.place.details.PlaceDetailsActivity
import com.downstairs.place.list.PlaceListFragment
import com.downstairs.place.list.PlacePageFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [PlaceModule::class],
    dependencies = [ApplicationComponent::class]
)
interface PlaceComponent {

    @Component.Factory
    interface Factory {
        fun create(
            applicationComponent: ApplicationComponent,
            placeModule: PlaceModule
        ): PlaceComponent
    }

    fun inject(activity: PlaceDetailsActivity)
    fun inject(fragment: PlaceListFragment)
    fun inject(fragment: PlacePageFragment)
}