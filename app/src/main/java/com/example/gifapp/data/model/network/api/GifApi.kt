package com.example.gifapp.data.model.network.api

import com.example.gifapp.data.model.entity.Response
import com.example.gifapp.data.model.network.RetrofitModule.PARAM_JSON
import com.example.gifapp.ui.presentation.gif.util.FragmentType

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifApi {

    @GET("{page_name}/{page}")
    suspend fun getGif(
        @Path("page_name") pageName: String = FragmentType.LATEST.value,
        @Path("page") page: Int = 0,
        @Query(PARAM_JSON) json: Boolean = true
    ): Response

}