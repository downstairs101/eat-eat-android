package com.downstairs.split.injection

import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.FeatureScope
import com.downstairs.split.SplitsFragment
import dagger.Component

@FeatureScope
@Component(modules = [SplitModule::class, ViewModelModule::class], dependencies = [CoreComponent::class])
interface SplitComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): SplitComponent
    }

    fun inject(fragment: SplitsFragment)

}