package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myapplication.database.TodosDatabase
import com.example.myapplication.ui.main.MainViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module

class ArchApplication : Application() {

    val appModule = module {
        single<TodosDatabase> {
            Room.databaseBuilder(
                applicationContext,
                TodosDatabase::class.java,
                "database"
            )
                .build()
        }

        single <WebServise> { Retrofit.retrofit.create(WebServise::class.java) }
        single<Repository> { Repository(get(), get(TodosDatabase::class).todosDao()) }
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ArchApplication)
            modules(appModule)
        }
    }
}