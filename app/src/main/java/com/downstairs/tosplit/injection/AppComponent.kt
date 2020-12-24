package com.downstairs.tosplit.injection

import com.downstairs.tosplit.MainActivity
import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.FeatureScope
import com.downstairs.tosplit.login.LoginFragment
import com.downstairs.tosplit.splash.SplashFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: SplashFragment)
}