package com.downstairs.data

import com.downstairs.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(private val interceptor: HttpRequestInterceptor) {

    init {
        val moshi = Moshi.Builder().add(UUIDAdapter()).build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(buildClient())
    }

    private fun buildClient() = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}