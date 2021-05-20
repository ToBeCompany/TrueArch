package com.example.myapplication.ui.main


import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.Repository
import com.example.myapplication.network.Resource
import com.example.myapplication.model.TodosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.lang.Exception


class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    init {
        loadTodos()
    }

    var todos: MutableLiveData<Resource<List<TodosItem>>> = MutableLiveData()

    fun loadTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                todos.postValue(Resource.Loading())
                todos.postValue(Resource.Success(repository.getTodos()))
            } catch (e: Exception) {

                todos.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    var flow: Flow<List<Int>> = flowOf(listOf())

    fun getNum() {
        flow = repository.getNum()
    }
}