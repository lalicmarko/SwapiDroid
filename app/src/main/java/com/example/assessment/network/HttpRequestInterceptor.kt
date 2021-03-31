package com.example.assessment.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Log.e(request.toString(), "BOBAN")
        return chain.proceed(request)
    }

    companion object {
        const val INTERCEPTOR_TAG = "INTERCEPTOR"
    }
}