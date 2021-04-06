package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.TodosItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : TodosItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : List<TodosItem>)

    @Query("DELETE FROM todos")
    suspend fun deleteAll()

    @Query("SELECT * FROM todos")
    fun getTodos(): Flow<List<TodosItem>>
}