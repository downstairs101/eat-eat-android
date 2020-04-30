package com.downstairs.injection

import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.FeatureScope
import dagger.Component

@FeatureScope
@Component(modules = [ViewModelModule::class], dependencies = [CoreComponent::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): AppComponent
    }
}