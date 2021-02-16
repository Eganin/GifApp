package com.example.gifapp.data.model.repository

import com.example.gifapp.data.model.network.RetrofitModule
import kotlinx.coroutines.*

class GifRepository : Repository {

    private val dispatcher =Dispatchers.IO

    override suspend fun getLatestGif()= withContext(dispatcher){
        RetrofitModule.apiGif.getLatestGif()
    }

    override suspend fun getTopGif()= withContext(dispatcher){
        RetrofitModule.apiGif.getTopGif()
    }

    override suspend fun getHotGif()= withContext(dispatcher){
        RetrofitModule.apiGif.getHotGif()
    }
}