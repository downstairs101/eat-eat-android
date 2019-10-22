package com.downstairs.injection

import android.content.Context
import com.downstairs.TestApplication
import com.downstairs.place.details.PlaceDetailsActivityTest
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.mockk.mockk
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidModule::class, TestModule::class])
interface TestAppComponent : AndroidInjector<TestApplication> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): TestAppComponent
    }

    fun inject(test:PlaceDetailsActivityTest)
}

@Module
class TestModule {

    @Provides
    @Singleton
    fun providesViewModelFactory()= mockk<ViewModelFactory>(relaxed = true)
}
