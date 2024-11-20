package com.example.currency_converter_app.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("API Request", "URL: ${request.url()}")
        return chain.proceed(request)
    }
}