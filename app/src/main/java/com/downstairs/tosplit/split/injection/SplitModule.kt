package com.downstairs.tosplit.split.injection

import androidx.lifecycle.ViewModel
import com.downstairs.core.http.HttpManager
import com.downstairs.core.injection.FeatureScope
import com.downstairs.core.injection.ViewModelKey
import com.downstairs.tosplit.BuildConfig
import com.downstairs.tosplit.split.data.SplitServiceApi
import com.downstairs.tosplit.split.list.SplitsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SplitModule {

    @FeatureScope
    @Provides
    fun providesSplitService(httpManager: HttpManager): SplitServiceApi {
        return httpManager.instantiate(SplitServiceApi::class.java, BuildConfig.BASE_URL)
    }

    @Module
    interface SplitViewModule {

        @Binds
        @IntoMap
        @ViewModelKey(SplitsViewModel::class)
        abstract fun bindSplitsViewModel(splitsViewModel: SplitsViewModel): ViewModel
    }
}