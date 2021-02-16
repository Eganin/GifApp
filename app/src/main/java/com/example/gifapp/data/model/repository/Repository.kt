package com.example.gifapp.data.model.repository

import com.example.gifapp.data.model.entity.GifResponse

interface Repository {

    suspend fun getLatestGif() : GifResponse
    suspend fun getTopGif() : GifResponse
    suspend fun getHotGif() : GifResponse
}