package com.downstairs.core.injection

import com.downstairs.core.http.HttpClient
import com.downstairs.core.http.HttpManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    internal fun providesHttpManager(
        okHttpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory
    ) = HttpManager(okHttpClient, converterFactory)

    @Provides
    @Singleton
    internal fun providesHttpClient() = HttpClient().instantiate()

    @Provides
    @Singleton
    internal fun providesMoshiConverter() = MoshiConverterFactory.create()
}