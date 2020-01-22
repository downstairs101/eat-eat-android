package com.downstairs.androidxtest.injection

import android.content.Context
import com.downstairs.TestApplication
import com.downstairs.place.data.PlaceDAO
import com.downstairs.place.details.PlaceDetailsActivityTest
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import io.mockk.mockk
import javax.inject.Singleton

@Singleton
@Component(modules = [TestViewModelFactoryModule::class, ViewModelModule::class, TestMock::class])
interface TestAppComponent : AndroidInjector<TestApplication> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): TestAppComponent
    }

    fun inject(test: PlaceDetailsActivityTest)
}

@Module
class TestMock {

    @Provides
    @Singleton
    fun providePlaceDAO(): PlaceDAO {
        return mockk(relaxed = true)
    }
}


