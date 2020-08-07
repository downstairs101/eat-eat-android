package com.downstairs.tosplit.injection

import com.downstairs.core.http.HttpManager
import com.downstairs.core.injection.FeatureScope
import com.downstairs.tosplit.BuildConfig
import com.downstairs.tosplit.split.data.SplitServiceApi
import dagger.Module
import dagger.Provides

@Module
class SplitModule {

    @FeatureScope
    @Provides
    fun providesSplitService(httpManager: HttpManager): SplitServiceApi {
        return httpManager.instantiate(SplitServiceApi::class.java, BuildConfig.BASE_URL)
    }
}