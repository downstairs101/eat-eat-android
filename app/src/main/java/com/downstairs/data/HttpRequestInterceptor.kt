package com.downstairs.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpRequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder().build()

        return chain.proceed(request)
    }


}
