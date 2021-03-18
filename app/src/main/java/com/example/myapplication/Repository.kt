package com.example.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Repository(context: Context) {
    private val reference: WebServise by lazy { Retrofit.retrofit.create(WebServise::class.java) }

    suspend fun getTodos():Todos?{
        return reference.getTodos().execute().body()
    }
}