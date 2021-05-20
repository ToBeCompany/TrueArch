package com.example.myapplication

import android.util.Log
import com.example.myapplication.database.TodosDao
import com.example.myapplication.model.TodosItem
import com.example.myapplication.network.WebServise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf


class Repository(
    private val reference: WebServise,
    private val wordDao: TodosDao
) {
    val listItems = listOf(1, 2, 3, 40, 5, 6, 7, 80, 9, 10)
    val listItems1 = listOf(10, 2, 30, 40, 50, 6, 70, 80, 90)

    var first = true
    fun getNum(): Flow<List<Int>> {
        return if (first) {
            first = !first
            Log.d("ITEM","1")

            flowOf(listItems)
        } else {
            Log.d("ITEM","2")

            flowOf(listItems1)
        }
    }

    suspend fun getTodos(): List<TodosItem> {
        refreshData()
        return wordDao.getTodos()
    }

    private suspend fun refreshData() {
        try {
            val result = reference.getTodos()
            insertTodos(result.body()!!)
        } catch (e: Exception) {
        }
    }

    private suspend fun insertTodos(todosItem: TodosItem) {
        wordDao.insert(todosItem)
    }

    private suspend fun insertTodos(todosItem: List<TodosItem>) {
        wordDao.insert(todosItem)
    }
}