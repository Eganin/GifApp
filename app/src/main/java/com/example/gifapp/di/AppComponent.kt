package com.example.gifapp.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gifapp.data.model.repository.GifRepository
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import com.example.gifapp.ui.presentation.gif.viewmodel.GifViewModel

class AppComponent{
    private val gifRepository = GifRepository()

    private fun getGifRepository() = gifRepository

    fun getGifViewModel(fragment: Fragment): GifViewModel =
        ViewModelProvider(
            fragment,
            GifViewModel.Factory(repository = getGifRepository())
        )[GifViewModel::class.java]


}