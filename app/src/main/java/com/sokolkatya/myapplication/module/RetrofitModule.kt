package com.sokolkatya.myapplication.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sokolkatya.myapplication.data.sourse.server.HttpServerInterceptor
import com.sokolkatya.myapplication.data.sourse.server.ServerApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HttpServerInterceptor())
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    val serverApi: ServerApi = retrofit.create(ServerApi::class.java)

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}