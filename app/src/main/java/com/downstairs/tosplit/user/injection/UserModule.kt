package com.downstairs.tosplit.user.injection

import com.downstairs.core.http.HttpManager
import com.downstairs.core.injection.FeatureScope
import com.downstairs.tosplit.BuildConfig
import com.downstairs.tosplit.user.data.UserServiceApi
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @FeatureScope
    @Provides
    fun providesUserService(httpManager: HttpManager): UserServiceApi {
        return httpManager.instantiate(UserServiceApi::class.java, BuildConfig.BASE_URL)
    }
}