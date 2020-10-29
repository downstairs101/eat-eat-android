package com.downstairs.tosplit.split.injection

import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.FeatureScope
import com.downstairs.tosplit.injection.ViewModelModule
import com.downstairs.tosplit.split.list.SplitListFragment
import dagger.Component

@FeatureScope
@Component(modules = [SplitModule::class, ViewModelModule::class], dependencies = [CoreComponent::class])
interface SplitComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): SplitComponent
    }

    fun inject(fragment: SplitListFragment)

}