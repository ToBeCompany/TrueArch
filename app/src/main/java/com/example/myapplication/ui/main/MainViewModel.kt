package com.example.myapplication.ui.main


import androidx.lifecycle.*
import com.example.myapplication.Repository
import com.example.myapplication.Resource
import com.example.myapplication.Todos
import com.example.myapplication.TodosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    init {
        loadTodos()
    }

    var todos: MutableLiveData<Resource<List<TodosItem>>> = MutableLiveData()

    fun loadTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                todos.postValue(Resource.Loading())
                todos.postValue(Resource.Success(repository.getTodos()))
            }
            catch (e : Exception){

                todos.postValue(Resource.Error(e.message.toString()))
            }
        }
    }
}