package com.example.myapplication.ui.main


import androidx.lifecycle.*
import com.example.myapplication.Repository
import com.example.myapplication.Resource
import com.example.myapplication.Todos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {

    init {
        loadTodos()
    }

    private val _todos: MutableLiveData<Resource<Todos>> = MutableLiveData()
    val todos: LiveData<Resource<Todos>> = _todos


    private fun loadTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            _todos.postValue(Resource.Loading<Todos>())
            _todos.postValue(Resource.Success(repository.getTodos() ?: Todos()))
        }
    }
}