package com.example.gifapp.ui.presentation.gif.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.repository.GifRepository
import com.example.gifapp.data.model.repository.Repository
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import kotlinx.coroutines.*

class GifViewModel(private val repository: Repository) : ViewModel() {

    private val dispatcher = Dispatchers.IO

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("$exception in $coroutineContext")
    }

    private val scope = CoroutineScope(SupervisorJob() + dispatcher + exceptionHandler)

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    private val _gif = MutableLiveData<GifResponse>()
    val gif: LiveData<GifResponse> = _gif


    fun loadGif(type: FragmentType) {
        scope.launch {
            _state.value = true
            _gif.value = when (type) {
                FragmentType.LATEST -> repository.getLatestGif()
                FragmentType.TOP -> repository.getTopGif()
                FragmentType.HOT -> repository.getHotGif()
            }
            _state.value = false
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: GifRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GifViewModel(repository = repository) as T
        }
    }
}