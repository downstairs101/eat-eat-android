package com.downstairs.split.injection

import androidx.lifecycle.ViewModel
import com.downstairs.eatat.core.injection.ViewModelKey
import com.downstairs.split.SplitsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SplitModule.SplitViewModule::class])
class SplitModule {

    @Module
    interface SplitViewModule {

        @Binds
        @IntoMap
        @ViewModelKey(SplitsViewModel::class)
        abstract fun bindSplitsViewModel(splitsViewModel: SplitsViewModel): ViewModel
    }
}