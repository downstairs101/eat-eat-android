package com.downstairs.tosplit.user.injection

import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.FeatureScope
import com.downstairs.tosplit.injection.ViewModelModule
import com.downstairs.tosplit.split.list.SplitListFragment
import com.downstairs.tosplit.user.register.UserRegistryFragment
import dagger.Component

@FeatureScope
@Component(modules = [UserModule::class], dependencies = [CoreComponent::class])
interface UserComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): UserComponent
    }

    fun inject(fragment: UserRegistryFragment)
}