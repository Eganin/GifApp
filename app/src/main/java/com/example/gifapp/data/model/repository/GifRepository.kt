package com.example.gifapp.data.model.repository

import com.example.gifapp.data.model.network.RetrofitModule
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import kotlinx.coroutines.*

class GifRepository : Repository {

    private val dispatcher =Dispatchers.IO

    override suspend fun getGif(type:FragmentType,page:Int)= withContext(dispatcher){
        RetrofitModule.apiGif.getGif(pageName = type.value,page=page).result
    }
}