package com.example.myapplication

import android.app.Application
import com.example.myapplication.ui.main.MainViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module

class ArchApplication : Application() {

        val appModule = module {
                single<WebServise> { Retrofit.retrofit.create(WebServise::class.java) }
                single<Repository> { Repository(get()) }
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