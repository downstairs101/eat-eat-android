package com.downstairs.tosplit.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.downstairs.core.injection.FeatureScope
import com.downstairs.core.injection.ViewModelFactory
import com.downstairs.core.injection.ViewModelKey
import com.downstairs.tosplit.split.list.SplitsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [ViewModelModule.SplitViewModule::class])
class ViewModelModule {

    @Provides
    @FeatureScope
    internal fun viewModelFactory(providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }

    @Module
    interface SplitViewModule {

        @Binds
        @IntoMap
        @ViewModelKey(SplitsViewModel::class)
        abstract fun bindSplitsViewModel(splitsViewModel: SplitsViewModel): ViewModel
    }
}
