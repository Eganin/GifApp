package com.example.gifapp.ui.presentation.gif.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.repository.GifRepository
import com.example.gifapp.data.model.repository.Repository
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import kotlinx.coroutines.*

class GifViewModel(private val repository: Repository) : ViewModel(){


    private val dispatcher = Dispatchers.Main

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("$exception in $coroutineContext")
    }

    private val scope = CoroutineScope(SupervisorJob()+dispatcher+exceptionHandler)

    private val _gif = MutableLiveData<List<GifResponse>>()
    val gif: LiveData<List<GifResponse>> = _gif


    fun loadGif(type: FragmentType) {
        scope.launch {
                Pager.page++
                Pager.count=0
                _gif.value = when (type) {
                    FragmentType.LATEST -> repository.getGif(type=type,page=Pager.page)
                    FragmentType.TOP -> repository.getGif(type=type,page=Pager.page)
                    FragmentType.HOT -> repository.getGif(type=type,page=Pager.page)
                }

        }

    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: GifRepository)
        : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GifViewModel(repository = repository) as T
        }
    }

}

object Pager {
    var count = 0
    var page = -1
}