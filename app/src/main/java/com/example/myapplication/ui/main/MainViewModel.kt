package com.example.myapplication.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ArchApplication
import com.example.myapplication.Repository
import com.example.myapplication.Resource
import com.example.myapplication.Todos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = (application as ArchApplication).repository

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