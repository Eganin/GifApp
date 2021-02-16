package com.example.gifapp.data.model.network.api

import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.network.RetrofitModule.PARAM_JSON
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("latest")
    suspend fun getLatestGif(@Query(PARAM_JSON) json : Boolean=true) : GifResponse

    @GET("hot")
    suspend fun getHotGif(@Query(PARAM_JSON) json : Boolean=true) : GifResponse

    @GET("top")
    suspend fun getTopGif(@Query(PARAM_JSON) json : Boolean=true) : GifResponse
}