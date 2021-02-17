package com.example.gifapp.data.model.repository

import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.ui.presentation.gif.util.FragmentType

interface Repository {
    suspend fun getGif(type: FragmentType,page:Int=0) : List<GifResponse>?
}