package com.downstairs.injection

import com.downstairs.MainActivity
import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
}