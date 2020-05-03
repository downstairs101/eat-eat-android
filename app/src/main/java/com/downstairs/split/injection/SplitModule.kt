package com.downstairs.split.injection

import com.downstairs.BuildConfig
import com.downstairs.eatat.core.http.HttpManager
import com.downstairs.eatat.core.injection.FeatureScope
import com.downstairs.split.data.SplitServiceApi
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