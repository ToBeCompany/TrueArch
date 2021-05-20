package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.TodosItem

@Database(entities = arrayOf(TodosItem::class), version = 1, exportSchema = false)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todosDao() : TodosDao
}