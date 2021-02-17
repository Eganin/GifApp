package com.example.gifapp.data.model.network.api

import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.entity.Response
import com.example.gifapp.data.model.network.RetrofitModule.PARAM_JSON
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifApi {

    @GET("latest/{page}")
    suspend fun getLatestGif(
        @Path("page") page: Int = 0,
        @Query(PARAM_JSON) json: Boolean = true
    ): Response

    @GET("hot/{page}")
    suspend fun getHotGif(
        @Path("page") page: Int = 0,
        @Query(PARAM_JSON) json: Boolean = true
    ): Response

    @GET("top/{page}")
    suspend fun getTopGif(
        @Path("page") page: Int = 0,
        @Query(PARAM_JSON) json: Boolean = true
    ): Response
}