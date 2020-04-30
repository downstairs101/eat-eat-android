package com.downstairs.eatat.core.http

import okhttp3.OkHttpClient
import javax.inject.Inject

class HttpClient @Inject
constructor() {

    fun instantiate(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}