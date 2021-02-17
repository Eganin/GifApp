package com.example.gifapp.ui.presentation.gif.viewmodel

import android.graphics.pdf.PdfDocument
import androidx.lifecycle.*
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.repository.GifRepository
import com.example.gifapp.data.model.repository.Repository
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import kotlinx.coroutines.*

class GifViewModel(private val repository: Repository) : ViewModel(){


    private val dispatcher = Dispatchers.Main.immediate

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("$exception in $coroutineContext")
    }

    private val scope = CoroutineScope(SupervisorJob()+dispatcher+exceptionHandler)

    private val _gif = MutableLiveData<GifResponse>()
    val gif: LiveData<GifResponse> = _gif


    fun loadGif(type: FragmentType) {
        scope.launch {
            if(Pager.count % 5 ==0 ) Pager.page++

            _gif.value = when (type) {
                FragmentType.LATEST -> repository.getGif(type=type,page=Pager.count)?.get(Pager.count)
                FragmentType.TOP -> repository.getGif(type=type,page=Pager.count)?.get(Pager.count)
                FragmentType.HOT -> repository.getGif(type=type,page=Pager.count)?.get(Pager.count)
            }
            Pager.count++
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
    var page = 0
}