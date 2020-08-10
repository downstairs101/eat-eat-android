package com.downstairs.core.http

import com.downstairs.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class HttpClient @Inject
constructor() {

    fun instantiate(httpAuthenticator: HttpAuthenticator): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .authenticator(httpAuthenticator)
            .build()
    }
}