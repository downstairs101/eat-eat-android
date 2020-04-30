package com.downstairs.eatat.core.http

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class HttpManager @Inject constructor(
    private val httpClient: OkHttpClient,
    private val converterFactory: Converter.Factory
) {

    fun <T> instantiate(serverClass: Class<T>): T {
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(serverClass)
    }
}