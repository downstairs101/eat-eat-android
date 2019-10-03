package com.downstairs.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(private val interceptor: HttpRequestInterceptor) {

    init {
        Retrofit.Builder()
            .baseUrl("http://localhost.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(buildClient())
    }

    private fun buildClient() = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}