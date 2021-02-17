package com.example.gifapp.data.model.repository

import com.example.gifapp.data.model.entity.GifResponse

interface Repository {

    suspend fun getLatestGif() : List<GifResponse>?
    suspend fun getTopGif() : List<GifResponse>?
    suspend fun getHotGif() : List<GifResponse>?
}