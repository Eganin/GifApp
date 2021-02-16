package com.example.gifapp.data.model.network

import com.example.gifapp.data.model.network.api.GifApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object RetrofitModule {
    private const val BASE_URL = "http://developerslife.ru/"

    const val PARAM_JSON = "json"

    private val json = Json{
        ignoreUnknownKeys=true
        coerceInputValues=true
    }

    private val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor = HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

    val apiGif : GifApi = retrofit.create()
}