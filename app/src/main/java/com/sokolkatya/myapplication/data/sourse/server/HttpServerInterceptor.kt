package com.sokolkatya.myapplication.data.sourse.server

import okhttp3.Interceptor
import okhttp3.Response

class HttpServerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()
        var originalHttpUrl = originalRequest.url
        originalHttpUrl = originalHttpUrl
                .newBuilder()
                .addQueryParameter(API_KEY, API_KEY_VALUE)
                .build()


        originalRequest = originalRequest
                .newBuilder()
                .url(originalHttpUrl)
                .build()

        return chain.proceed(originalRequest)
    }

    companion object {

        private const val API_KEY = "api_key"

        private const val API_KEY_VALUE = "1b553bd11dccb3232069cc5075fc9de2"
    }
}