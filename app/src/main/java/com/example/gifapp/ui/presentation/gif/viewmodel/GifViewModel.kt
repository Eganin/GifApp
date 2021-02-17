package com.example.gifapp.ui.presentation.gif.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.repository.GifRepository
import com.example.gifapp.data.model.repository.Repository
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import kotlinx.coroutines.*

class GifViewModel(private val repository: Repository) : ViewModel(){


    private val dispatcher = Dispatchers.IO

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("$exception in $coroutineContext")
    }

    private val scope = CoroutineScope(dispatcher)

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    private val _gif = MutableLiveData<GifResponse>()
    val gif: LiveData<GifResponse> = _gif


    fun loadGif(type: FragmentType) {
        viewModelScope.launch {
            _state.value = true
            _gif.value = when (type) {
                FragmentType.LATEST -> repository.getLatestGif()?.get(Pager.count)
                FragmentType.TOP -> repository.getTopGif()?.get(Pager.count)
                FragmentType.HOT -> repository.getHotGif()?.get(Pager.count)
            }

            _state.value = false
            Pager.count++
        }

    }



    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: GifRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GifViewModel(repository = repository) as T
        }
    }

}

object Pager {
    var count = 0
}