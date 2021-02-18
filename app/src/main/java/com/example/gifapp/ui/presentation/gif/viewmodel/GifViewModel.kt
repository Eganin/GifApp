package com.example.gifapp.ui.presentation.gif.viewmodel

import androidx.lifecycle.*
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.data.model.repository.GifRepository
import com.example.gifapp.data.model.repository.Repository
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import kotlinx.coroutines.*

class GifViewModel(private val repository: Repository) : ViewModel(){


    private val dispatcher = Dispatchers.Main

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Pager.page--
        _state.value=State.Error
    }

    private val scope = CoroutineScope(SupervisorJob()+dispatcher+exceptionHandler)

    private val _gif = MutableLiveData<List<GifResponse>>()
    val gif: LiveData<List<GifResponse>> = _gif

    private val _state = MutableLiveData<State>(State.Default)
    val state : LiveData<State> = _state


    fun loadGif(type: FragmentType) {
        scope.launch {
                _state.value=State.Loading
                Pager.page++
                _gif.value = when (type) {
                    FragmentType.LATEST -> repository.getGif(type=type,page=Pager.page)
                    FragmentType.TOP -> repository.getGif(type=type,page=Pager.page)
                    FragmentType.HOT -> repository.getGif(type=type,page=Pager.page)
                }
                _state.value=State.Success
        }

    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: GifRepository)
        : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GifViewModel(repository = repository) as T
        }
    }

    sealed class State {
        object Default : State()
        object Loading : State()
        object Error : State()
        object Success : State()
    }

}

object Pager {
    var count = 0
    var page = -1
}