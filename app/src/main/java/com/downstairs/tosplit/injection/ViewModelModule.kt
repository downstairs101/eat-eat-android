package com.downstairs.tosplit.injection

import androidx.lifecycle.ViewModelProvider
import com.downstairs.core.injection.ViewModelFactory
import com.downstairs.tosplit.split.injection.SplitModule
import dagger.Binds
import dagger.Module

@Module(includes = [SplitModule.SplitViewModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
