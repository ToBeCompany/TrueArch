package com.example.myapplication

import com.example.myapplication.database.TodosDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count


class Repository(
    private val reference: WebServise,
    private val wordDao: TodosDao
) {

    private var cache: Todos? = null

    val allTodos: Flow<List<TodosItem>> = wordDao.getTodos()

    suspend fun getTodos(): Todos? {
        if (cache == null) {
            cache = reference.getTodos()
            insertTodos(cache!!)
        }
        return cache
    }

    suspend fun insertTodos(todosItem : TodosItem){
        wordDao.insert(todosItem)
    }
    suspend fun insertTodos(todosItem : List<TodosItem>){
        wordDao.insert(todosItem)
    }
}