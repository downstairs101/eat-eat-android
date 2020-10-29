package com.downstairs.tosplit.home

import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.FeatureScope
import com.downstairs.tosplit.user.injection.UserModule
import dagger.Component

@FeatureScope
@Component(
    modules = [UserModule::class],
    dependencies = [CoreComponent::class]
)
interface HomeComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}