package com.example.myapplication

import com.example.myapplication.database.TodosDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count


class Repository(
    private val reference: WebServise,
    private val wordDao: TodosDao
) {

    suspend fun getTodos():List<TodosItem>{
        refreshData()
        return wordDao.getTodos()
    }

    private suspend fun refreshData() {
        val result = reference.getTodos()
        if (result.isSuccessful){
            insertTodos(result.body()!!)
        }
    }

    private suspend fun insertTodos(todosItem : TodosItem){
        wordDao.insert(todosItem)
    }
    private suspend fun insertTodos(todosItem : List<TodosItem>){
        wordDao.insert(todosItem)
    }
}