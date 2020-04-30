package com.downstairs.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.downstairs.eatat.core.injection.FeatureScope
import com.downstairs.eatat.core.injection.ViewModelFactory
import dagger.Module
import dagger.Provides

import javax.inject.Provider

@Module
class ViewModelModule {

    @Provides
    @FeatureScope
    internal fun viewModelFactory(providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }
}